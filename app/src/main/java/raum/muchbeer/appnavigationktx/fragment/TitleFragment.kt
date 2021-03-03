package raum.muchbeer.appnavigationktx.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import raum.muchbeer.appnavigationktx.R
import raum.muchbeer.appnavigationktx.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_title, container, false)
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,R.layout.fragment_title, container, false)
        binding.playButton.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.titleFrag_to_gameFrag)        }

        //tell the fragment that we are going to have our menu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_about, menu)
           }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      return  when(item!!.itemId) {
            R.id.aboutMenu -> {
                requireView().findNavController().navigate(R.id.titleFrag_to_aboutFrag)
                true
            }
            else -> {
                NavigationUI.onNavDestinationSelected(item!!, requireView()!!.findNavController())
                        || super.onOptionsItemSelected(item)
            }
        }

    //    return
    /*when(item.itemId) {
            R.id.aboutImage -> {
               requireView().findNavController().navigate(R.id.titleFrag_to_aboutFrag)
            true
            } else ->*/

    }
}