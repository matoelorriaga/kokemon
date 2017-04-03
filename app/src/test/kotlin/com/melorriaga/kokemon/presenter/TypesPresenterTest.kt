package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.types.TypesInteractor
import com.melorriaga.kokemon.model.Type
import com.melorriaga.kokemon.presenter.types.TypesPresenterImpl
import com.melorriaga.kokemon.view.types.TypesView
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

class TypesPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var view: TypesView

    @Mock
    lateinit var interactor: TypesInteractor

    @InjectMocks
    lateinit var presenter: TypesPresenterImpl

    @Test
    fun testOnStart_firstStart() {
        presenter.onStart(true)

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonTypes(presenter)
    }

    @Test
    fun testOnStart_networkRequestInProgress() {
        `when`(interactor.networkRequestInProgress).thenReturn(true)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showLoadingIndicator()
        verify(view, times(1)).showPokemonTypes(anyList())
    }

    @Test
    fun testOnStart() {
        `when`(interactor.networkRequestInProgress).thenReturn(false)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showPokemonTypes(anyList())
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
    fun testGetPokemonTypes() {
        presenter.getPokemonTypes()

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonTypes(presenter)
    }

    @Test
    fun testOnGetPokemonTypesListener_onSuccess() {
        val pokemonTypes = listOf(
                Type(1, "normal"),
                Type(2, "fighting"),
                Type(3, "flying")
        )
        val captor = argumentCaptor<List<Type>>()

        presenter.onSuccess(pokemonTypes)

        verify(view, times(1)).showPokemonTypes(captor.capture())
        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showDoneMessage()

        assertThat(captor.firstValue, `is`(pokemonTypes))
    }

    @Test
    fun testOnGetPokemonTypesListener_onFailure() {
        presenter.onFailure()

        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showErrorMessage()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view, interactor)
    }

}
