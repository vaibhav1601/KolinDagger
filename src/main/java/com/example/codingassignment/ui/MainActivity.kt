package com.example.codingassignment.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.codingassignment.R
import com.example.codingassignment.data.model.Owner
import com.example.codingassignment.data.model.Repo
import com.example.codingassignment.ui.viewmodel.GitRepoDataViewModel
import com.example.codingassignment.ui.viewmodel.ReposViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: GitRepoDataViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

      //  setViewsBasedOnRepo()
    }

/*    private fun setViewsBasedOnRepo() {

        GlobalScope.launch(Dispatchers.Main) {

            val repoList: MutableList<Repo> = mutableListOf()
            val own = Owner(2, "vaibav", "adgsagjd")
            val repo = Repo(
                1,
                "vaibhav",
                "vaibhav kulkarni",
                "ss",
                "jj",
                32,
                343,
                "marathi",
                44,
                own,
                "sads",
                "3",
                4
            )
            repoList.add(repo)

            viewModel.insertAllData(repoList)

        }

       *//* GlobalScope.launch(Dispatchers.Main) {

            val size = withContext(Dispatchers.Default) { viewModel.databaseSize() }
        }*//*

        //
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}