package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.MainInteractor
import com.melorriaga.kokemon.view.main.MainView
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import java.util.Arrays.asList

class MainPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var view: MainView

    @Mock
    lateinit var interactor: MainInteractor

    @InjectMocks
    lateinit var presenter: MainPresenterImpl

    @Test
    fun testOnStart_firstTime() {
        presenter.onStart(true)

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonNames(presenter)
    }

    @Test
    fun testOnStart_networkRequestInProgress() {
        `when`(interactor.networkRequestInProgress).thenReturn(true)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showLoadingIndicator()
        verify(view, times(1)).showPokemonNames(anyList())
    }

    @Test
    fun testOnStart() {
        `when`(interactor.networkRequestInProgress).thenReturn(false)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showPokemonNames(anyList())
    }

    @Test
    fun testOnPresenterDestroyed() {
        presenter.onPresenterDestroyed()

        verify(interactor, times(1)).cancelNetworkRequest()
    }

    @Test
    fun testGetPokemonNames() {
        presenter.getPokemonNames()

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonNames(presenter)
    }

    @Test
    fun testOnGetPokemonNamesListener_onSuccess() {
        val pokemonNames = asList("bulbasaur", "charmander", "squirtle")
        val captor = argumentCaptor<List<String>>()

        presenter.onSuccess(pokemonNames)

        verify(view, times(1)).showPokemonNames(captor.capture())
        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showDoneMessage()

        assertThat(captor.firstValue, `is`(pokemonNames))
    }

    @Test
    fun testOnGetPokemonNamesListener_onFailure() {
        presenter.onFailure()

        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showErrorMessage()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view, interactor)
    }

}
