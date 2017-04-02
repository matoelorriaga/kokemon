package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.details.DetailsInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.details.DetailsPresenterImpl
import com.melorriaga.kokemon.view.details.DetailsView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.powermock.reflect.Whitebox

class DetailsPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var view: DetailsView

    @Mock
    lateinit var interactor: DetailsInteractor

    @InjectMocks
    lateinit var presenter: DetailsPresenterImpl

    val pokemonId = 1
    val pokemon = Pokemon(1, "bulbasaur")

    @Test
    fun testOnStart_firstStart() {
        val captor = argumentCaptor<Int>()

        `when`(view.getPokemonId()).thenReturn(pokemonId)

        presenter.onStart(true)

        verify(view, times(1)).getPokemonId()
        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonDetails(captor.capture(), eq(presenter))

        assertThat(captor.firstValue, `is`(pokemonId))
    }

    @Test
    fun testOnStart_networkRequestInProgress() {
        val captor = argumentCaptor<Pokemon>()

        Whitebox.setInternalState(presenter, "pokemon", pokemon)

        `when`(interactor.networkRequestInProgress).thenReturn(true)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showLoadingIndicator()
        verify(view, times(1)).showPokemonDetails(captor.capture())

        assertThat(captor.firstValue, `is`(pokemon))
    }

    @Test
    fun testOnStart_networkRequestInProgress_pokemonNotAvailable() {
        `when`(interactor.networkRequestInProgress).thenReturn(true)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showLoadingIndicator()
    }

    @Test
    fun testOnStart() {
        val captor = argumentCaptor<Pokemon>()

        Whitebox.setInternalState(presenter, "pokemon", pokemon)

        `when`(interactor.networkRequestInProgress).thenReturn(false)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showPokemonDetails(captor.capture())

        assertThat(captor.firstValue, `is`(pokemon))
    }

    @Test
    fun testOnStart_pokemonNotAvailable() {
        `when`(interactor.networkRequestInProgress).thenReturn(false)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
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
    fun testGetPokemonDetails() {
        val captor = argumentCaptor<Int>()

        presenter.getPokemonDetails(pokemonId)

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getPokemonDetails(captor.capture(), eq(presenter))

        assertThat(captor.firstValue, `is`(pokemonId))
    }

    @Test
    fun testOnGetPokemonDetailsListener_onSuccess() {
        val captor = argumentCaptor<Pokemon>()

        presenter.onSuccess(pokemon)

        verify(view, times(1)).showPokemonDetails(captor.capture())
        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showDoneMessage()

        assertThat(captor.firstValue, `is`(pokemon))
    }

    @Test
    fun testOnGetPokemonDetailsListener_onFailure() {
        presenter.onFailure()

        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showErrorMessage()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view, interactor)
    }

}
