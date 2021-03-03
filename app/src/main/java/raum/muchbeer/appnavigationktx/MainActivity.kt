package raum.muchbeer.appnavigationktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import raum.muchbeer.appnavigationktx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_main)
    val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout
       val navController= findNavController(R.id.myNavHost)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)


        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        navController.addOnDestinationChangedListener { nc:NavController, nd:NavDestination, bundle: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
        //But in Fragment you need to call context first
        //context!!.getString(R.string.app_name)

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    //This overide happen when the up button is pressed in the up left conner back button pressed
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHost)
        //below return is used when we have single NavHost only without other views such as navController
      //  return navController.navigateUp()
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}