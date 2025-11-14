package com.bit.purple.project.ui.screens.Dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * TODO: You will need to provide this Repository using Hilt
 *
 * Example (in a new 'data' or 'domain' package):
 * interface NoteRepository {
 * fun getAllNotes(): Flow<List<Note>>
 * }
 *
 * class NoteRepositoryImpl @Inject constructor() : NoteRepository {
 * override fun getAllNotes(): Flow<List<Note>> = flowOf(sampleNotes) // Connect to Room/API here
 * }
 *
 * // And in your Hilt module:
 * @Binds
 * abstract fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
 *
 */

@HiltViewModel
class DashboardViewModel @Inject constructor(
    // private val noteRepository: NoteRepository // TODO: Uncomment when repository is ready
) : ViewModel() {

    // --- Private flows for internal logic ---

    // Holds all notes from the database.
    // TODO: Replace 'flowOf(sampleNotes)' with 'noteRepository.getAllNotes()'
    private val _allNotesFlow: Flow<List<Note>> = flowOf(sampleNotes)

    // Holds the current search query text.
    private val _searchQuery = MutableStateFlow("")

    // Holds the grid/list view state.
    private val _isGridView = MutableStateFlow(false)

    // --- Public StateFlow for the UI ---

    // This is the single source of truth for the UI.
    val uiState: StateFlow<DashboardScreenState> = combine(
        _allNotesFlow,
        _searchQuery,
        _isGridView
    ) { allNotes, query, isGrid ->

        // --- Filtering Logic ---
        val filteredNotes = if (query.isBlank()) {
            allNotes
        } else {
            allNotes.filter { note ->
                note.title.contains(query, ignoreCase = true) ||
                        note.summary.contains(query, ignoreCase = true) ||
                        note.topic.contains(query, ignoreCase = true)
            }
        }

        // --- State Emission ---
        DashboardScreenState(
            isLoading = false, // Data is now loaded
            displayedNotes = filteredNotes,
            totalNoteCount = allNotes.size,
            searchQuery = query,
            isGridView = isGrid
        )
    }
        // Set isLoading = true initially
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DashboardScreenState(isLoading = true)
        )

    // --- Public Event Handlers ---

    /**
     * Called when the user types in the search bar.
     */
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    /**
     * Called when the user clicks the grid/list toggle button.
     */
    fun onToggleView() {
        _isGridView.value = !_isGridView.value
    }

    /**
     * Called when the user clicks the filter button.
     */
    fun onFilterClick() {
        // TODO: Implement filter logic (e.g., show a BottomSheet)
    }
}

// --- Dummy Data (Remove this once your Repository is working) ---
 val sampleNotes = listOf(
    Note(1, "I and my husbanb leaving in the house,", "Sunday He brought the person home....", "Sunday Topic", "08:11 PM"),
    Note(2, "Ministering: Pastor David Adeoye", "Sunday Topic", "Sunday Topic", "08:11 PM"),
    Note(3, "Ministering: Pastor David Adeoye", "Sunday Topic", "Sunday Topic", "08:11 PM"),
    Note(4, "Ministering: Pastor David Adeoye", "Sunday Topic", "Sunday Topic", "08:11 PM"),
    Note(5, "I and my husbanb leaving in the house,", "Sunday He brought the person home....", "Sunday Topic", "08:11 PM"),
    Note(6, "Ministering: Pastor David Adeoye", "Sunday Topic", "Sunday Topic", "08:11 PM")
)