package com.melorriaga.kokemon.presenter

import com.melorriaga.kokemon.interactor.types.TypePageInteractor
import com.melorriaga.kokemon.model.Pokemon
import com.melorriaga.kokemon.presenter.types.TypePagePresenterImpl
import com.melorriaga.kokemon.view.types.TypePageView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

class TypePagePresenterTest : BasePresenterTest() {

    @Mock
    lateinit var view: TypePageView

    @Mock
    lateinit var interactor: TypePageInteractor

    @InjectMocks
    lateinit var presenter: TypePagePresenterImpl

    val typeId = 1

    @Test
    fun testOnStart_firstStart() {
        val captor = argumentCaptor<Int>()

        `when`(view.getPokemonTypeId()).thenReturn(typeId)

        presenter.onStart(true)

        verify(view, times(1)).getPokemonTypeId()
        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getAllPokemonOfType(captor.capture(), eq(presenter))

        assertThat(captor.firstValue, `is`(typeId))
    }

    @Test
    fun testOnStart_networkRequestInProgress() {
        `when`(interactor.networkRequestInProgress).thenReturn(true)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showLoadingIndicator()
        verify(view, times(1)).showAllPokemonOfType(anyList())
    }

    @Test
    fun testOnStart() {
        `when`(interactor.networkRequestInProgress).thenReturn(false)

        presenter.onStart(false)

        verify(interactor, times(1)).networkRequestInProgress
        verify(view, times(1)).showAllPokemonOfType(anyList())
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
    fun testGetAllPokemonOfType() {
        val captor = argumentCaptor<Int>()

        presenter.getAllPokemonOfType(typeId)

        verify(view, times(1)).showLoadingIndicator()
        verify(interactor, times(1)).getAllPokemonOfType(captor.capture(), eq(presenter))

        assertThat(captor.firstValue, `is`(typeId))
    }

    @Test
    fun testOnGetAllPokemonOfTypeListener_onSuccess() {
        val pokemonList = listOf(
                Pokemon(1, "bulbasaur"),
                Pokemon(2, "charmander"),
                Pokemon(3, "squirtle")
        )
        val captor = argumentCaptor<List<Pokemon>>()

        presenter.onSuccess(pokemonList)

        verify(view, times(1)).showAllPokemonOfType(captor.capture())
        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showDoneMessage()

        assertThat(captor.firstValue, Matchers.`is`(pokemonList))
    }

    @Test
    fun testOnGetAllPokemonOfTypeListener_onFailure() {
        presenter.onFailure()

        verify(view, times(1)).hideLoadingIndicator()
        verify(view, times(1)).showErrorMessage()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view, interactor)
    }

}
