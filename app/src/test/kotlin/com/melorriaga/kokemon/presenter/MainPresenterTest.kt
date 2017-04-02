package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.main.MainInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.main.MainPresenterImpl
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
    fun testOnStart_firstStart() {
        presenter.onStart(true)

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonList(presenter)
    }

    @Test
    fun testOnStart_networkRequestInProgress() {
        `when`(interactor.networkRequestInProgress).thenReturn(true)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showLoadingIndicator()
        verify(view, times(1)).showPokemonList(anyList())
    }

    @Test
    fun testOnStart() {
        `when`(interactor.networkRequestInProgress).thenReturn(false)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showPokemonList(anyList())
    }

    @Test
    fun testOnStop() {
        presenter.onStop()

        verify(view, times(1)).hideLoadingIndicator()
    }

    @Test
    fun testOnPresenterDestroyed() {
        presenter.onPresenterDestroyed()

        verify(interactor, times(1)).cancelNetworkRequest()
    }

    @Test
    fun testGetPokemonList() {
        presenter.getPokemonList()

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonList(presenter)
    }

    @Test
    fun testOnGetPokemonListListener_onSuccess() {
        val pokemonList = asList(
                Pokemon(1, "bulbasaur"),
                Pokemon(2, "charmander"),
                Pokemon(3, "squirtle")
        )
        val captor = argumentCaptor<List<Pokemon>>()

        presenter.onSuccess(pokemonList)

        verify(view, times(1)).showPokemonList(captor.capture())
        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showDoneMessage()

        assertThat(captor.firstValue, `is`(pokemonList))
    }

    @Test
    fun testOnGetPokemonListListener_onFailure() {
        presenter.onFailure()

        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showErrorMessage()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view, interactor)
    }

}
