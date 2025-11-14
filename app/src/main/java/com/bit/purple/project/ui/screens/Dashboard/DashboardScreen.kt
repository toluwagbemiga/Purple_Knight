package com.bit.purple.project.ui.screens.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bit.purple.project.R

// TODO: Add this import from your project's R file

/**
 * This is the "Route" composable you'll add to your NavGraph.
 * It connects the ViewModel to the (dumb) Screen composable.
 */
@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel(),
    onNavigateToNote: (Int) -> Unit // Event for navigation
) {
    // Collect the state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()

    DashboardScreen(
        modifier = modifier,
        state = uiState,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onToggleView = viewModel::onToggleView,
        onFilterClick = viewModel::onFilterClick,
        onNoteClick = { note -> onNavigateToNote(note.id) }
    )
}

/**
 * This is the "dumb" screen. It only receives state and
 * emits events. It has no knowledge of the ViewModel.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    state: DashboardScreenState,
    onSearchQueryChange: (String) -> Unit,
    onToggleView: () -> Unit,
    onFilterClick: () -> Unit,
    onNoteClick: (Note) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            // Use the state for the note count
            DashboardBottomBar(
                noteCount = state.totalNoteCount,
                onToggleView = onToggleView
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                // Apply the padding here to avoid UI overlapping
                .padding(paddingValues)
                // Add your screen's own horizontal padding
                .padding(horizontal = 16.dp)
        ) {
            // Spacer for a little room from the status bar
            Spacer(modifier = Modifier.height(16.dp))

            // --- Top Header ---
            Text(
                text = "Notes",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Search and Filter ---
            DashboardSearchAndFilter(
                searchQuery = state.searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onFilterClick = onFilterClick
            )
            Spacer(modifier = Modifier.height(16.dp))

            // --- Content (Empty or List) ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // This makes it fill all available space
                contentAlignment = Alignment.Center
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else if (state.displayedNotes.isEmpty()) {
                    DashboardEmptyView()
                } else {
                    // TODO: You can use state.isGridView here to switch
                    // between a LazyColumn and a LazyVerticalGrid
                    DashboardNotesList(
                        notes = state.displayedNotes,
                        onNoteClick = onNoteClick // Pass the click event down
                    )
                }
            }
        }
    }
}


@Composable
fun DashboardSearchAndFilter(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // --- Search Bar ---
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Search", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search",
                    tint = Color.Gray
                )

            },

            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                // Using a light gray background for the search field
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ),
            singleLine = true
        )
            Icon(
                painter = painterResource(id = R.drawable.search_button), // Icon for filtering
                contentDescription = "Filter Notes",

            )

    }
}

@Composable
fun DashboardNotesList(
    notes: List<Note>,
    onNoteClick: (Note) -> Unit, // Add this
    modifier: Modifier = Modifier
) {
    // This Surface creates the rounded white card container for the list
    Surface(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp // Adds a subtle shadow like in the image
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp) // Padding inside the card
        ) {
            items(notes, key = { it.id }) { note ->
                DashboardNoteItem(
                    note = note,
                    onNoteClick = onNoteClick // Pass click to item
                )
                Divider(
                    color = Color.Gray.copy(alpha = 0.1f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun DashboardNoteItem(
    note: Note,
    onNoteClick: (Note) -> Unit // Add this
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNoteClick(note) } // Make the item clickable
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = note.timestamp,
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Only show summary if it's not empty
        if (note.summary.isNotEmpty()) {
            Text(
                text = note.summary,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        Text(
            text = note.topic,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun DashboardEmptyView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // TODO: Replace with your actual drawable from your XML files
            // painter = painterResource(id = R.drawable.ic_empty_notes_placeholder)
            Icon(
                // Placeholder:
                painter = painterResource(id = android.R.drawable.ic_menu_agenda),
                contentDescription = "No notes",
                modifier = Modifier.size(120.dp),
                tint = Color.Gray.copy(alpha = 0.5f)
                // Use Color.Unspecified to show your drawable's original colors
                // tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "You do not have any notes yet.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun DashboardBottomBar(
    noteCount: Int,
    onToggleView: () -> Unit // Add this
) {
    // This Row is the content for the Scaffold's bottomBar slot
    Row(
        modifier = Modifier
            .fillMaxWidth()
            // Padding *inside* the bar
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$noteCount Notes",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        IconButton(onClick = onToggleView) { // Use the event
            Icon(
                imageVector = Icons.Default.GridView,
                contentDescription = "Grid View",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// --- Previews ---

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun DashboardScreenPopulatedPreview() {
    MaterialTheme {
        DashboardScreen(
            state = DashboardScreenState(
                isLoading = false,
                displayedNotes = sampleNotes, // Uses the dummy list from the VM file
                totalNoteCount = 992
            ),
            onSearchQueryChange = {},
            onToggleView = {},
            onFilterClick = {},
            onNoteClick = {}
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun DashboardScreenEmptyPreview() {
    MaterialTheme {
        DashboardScreen(
            state = DashboardScreenState(
                isLoading = false,
                displayedNotes = emptyList(), // Pass an empty list
                totalNoteCount = 0
            ),
            onSearchQueryChange = {},
            onToggleView = {},
            onFilterClick = {},
            onNoteClick = {}
        )
    }
}