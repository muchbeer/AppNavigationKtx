package raum.muchbeer.appnavigationktx.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import raum.muchbeer.appnavigationktx.R
import raum.muchbeer.appnavigationktx.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //pop up to (a certain fragment trigger back button to delete all fragment btwn and it self
        //if the inclusive pop up is true
        val binding:FragmentGameWonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won,container, false)

        binding.nextMatchButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.gameWonFrag_to_gameFrag)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_winner, menu)
        if(null == getIntentType().resolveActivity(requireActivity()!!.packageManager)) {
            menu?.findItem(R.id.shareMenu)?.setVisible(false)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.shareMenu -> {
               shareData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getIntentType() : Intent {
        var retrieveArgs = GameWonFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(activity, "Retrieved number of answers is : ${retrieveArgs.numAnswers}", Toast.LENGTH_LONG).show()

       /* val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text,
                                    retrieveArgs.numAnswers, retrieveArgs.numCorrect))*/

        val sendIntent = ShareCompat.IntentBuilder.from(requireActivity()).setText(getString(
            R.string.share_success_text, retrieveArgs.numCorrect, retrieveArgs.numAnswers
        )).setType("text/plain")
            .intent

        return sendIntent
    }

    fun shareData() {
        startActivity(getIntentType())
    }

}