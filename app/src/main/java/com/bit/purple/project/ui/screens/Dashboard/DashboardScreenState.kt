package com.bit.purple.project.ui.screens.Dashboard

/**
 * A simple data class to represent a note.
 * You might move this to a 'domain.model' package later.
 */
data class Note(
    val id: Int,
    val title: String,
    val summary: String,
    val topic: String,
    val timestamp: String
)

/**
 * Represents all the data your screen needs to draw itself.
 * This is the single source of truth for the DashboardScreen.
 */
data class DashboardScreenState(
    /** True if data is being loaded from the database. */
    val isLoading: Boolean = true,

    /** The list of notes to display (could be filtered). */
    val displayedNotes: List<Note> = emptyList(),

    /** The total number of notes, regardless of search. */
    val totalNoteCount: Int = 0,

    /** The current text in the search bar. */
    val searchQuery: String = "",

    /** True if the user has toggled the view to a grid. */
    val isGridView: Boolean = false
)