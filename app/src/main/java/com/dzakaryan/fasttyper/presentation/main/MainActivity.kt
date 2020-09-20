package com.dzakaryan.fasttyper.presentation.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dzakaryan.fasttyper.R
import com.dzakaryan.fasttyper.presentation.core.DrawerLocker
import com.dzakaryan.fasttyper.presentation.core.NavigationHeaderView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), DrawerLocker, NavigationHeaderView {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navigationHeaderView: View
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        initDrawerLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun setDrawerLocked(shouldLock: Boolean) {
        if (shouldLock) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    private fun initDrawerLayout() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationHeaderView = navigationView.getHeaderView(0)
        val navController = findNavController(R.id.nav_host_fragment)
        // menuIDs must be the same as destination IDs
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.typingStartFragment,
                R.id.statisticsFragment,
                R.id.leaderboardFragment,
                R.id.settingsFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)

        navigationView.menu.findItem(R.id.action_logout)
            .setOnMenuItemClickListener { menuItem: MenuItem? ->
                viewModel.onLogoutClick()
                true
            }
        //navigationView.setNavigationItemSelectedListener(this)
    }

    override fun getHeaderImageView(): ImageView =
        navigationHeaderView.findViewById(R.id.headerImage)

    override fun getDisplayNameView(): TextView =
        navigationHeaderView.findViewById(R.id.headerDisplayName)

    override fun getEmailView(): TextView =
        navigationHeaderView.findViewById(R.id.headerEmail)

//    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
//        if (menuItem.itemId == R.id.action_logout) {
//            viewModel.onLogoutClick()
//            findNavController().navigate(R.id.loginFragment)
//            drawerLayout.close()
//            return true
//        }
//        return false
//    }
}