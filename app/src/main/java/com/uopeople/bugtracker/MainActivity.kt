package com.uopeople.bugtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.uopeople.bugtracker.data.AppDatabase
import com.uopeople.bugtracker.data.Issue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val dao = database.issueDao()


        lifecycleScope.launch(Dispatchers.IO) {

            try {
                dao.insert(Issue(title = "Login issue", description = "The app closes automatically.ً", priority = "High", status = "Open", createdAt = System.currentTimeMillis()))
            } catch (e: Exception) { }

            val allIssues = dao.getAll()

            withContext(Dispatchers.Main) {
                setContent {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Bug Tracker - Offline First App", style = MaterialTheme.typography.titleLarge)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Number of issues stored locallyً: ${allIssues.size}")
                            Spacer(modifier = Modifier.height(8.dp))
                            if (allIssues.isNotEmpty()) {
                                Text(text = "The last Issue: ${allIssues[0].title}")
                                Text(text = "State: ${allIssues[0].status}")
                            }
                        }
                    }
                }
            }
        }
    }
}
