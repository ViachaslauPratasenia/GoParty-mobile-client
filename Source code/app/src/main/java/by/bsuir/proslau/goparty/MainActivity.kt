package by.bsuir.proslau.goparty

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import by.bsuir.proslau.goparty.ui.all_events.events_by_category.CategoryFragment
import by.bsuir.proslau.goparty.ui.all_events.favorite.FavoriteFragment
import by.bsuir.proslau.goparty.ui.profile.ProfileFragment
import by.bsuir.proslau.goparty.ui.all_events.recommended.RecommendedFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val recommendedFragment = RecommendedFragment()
    val eventsFragment = CategoryFragment()
    val favoriteFragment = FavoriteFragment()
    val profileFragment = ProfileFragment()
    val fragmentManager : FragmentManager = supportFragmentManager
    var activeFragment : Fragment = recommendedFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_recommended -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(recommendedFragment).commit()
                activeFragment = recommendedFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_events -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(eventsFragment).commit()
                activeFragment = eventsFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(favoriteFragment).commit()
                activeFragment = favoriteFragment
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
                activeFragment = profileFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        fragmentManager.beginTransaction().add(R.id.main_container, recommendedFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, eventsFragment).hide(eventsFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, favoriteFragment).hide(favoriteFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, profileFragment).hide(profileFragment).commit()
    }


}
