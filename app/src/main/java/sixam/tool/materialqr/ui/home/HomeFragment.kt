package sixam.tool.materialqr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import sixam.tool.materialqr.R
import net.glxn.qrgen.android.QRCode

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val editCode: EditText = root.findViewById(R.id.editCode)
        val btnCreate: Button = root.findViewById(R.id.btnCreate)
        val qrPreview: ImageView = root.findViewById(R.id.qrPreview)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            btnCreate.setOnClickListener {
                val text = editCode.text.toString()

                if (text.isBlank() || text.isEmpty()) {
                    Toast.makeText(activity, R.string.empty_input_error,
                        Toast.LENGTH_SHORT).show()

                    return@setOnClickListener
                }

                val qr = QRCode.from(text)
                val previewQR = qr.withSize(1000,1000).bitmap()
                qrPreview.setImageBitmap(previewQR)
            }
        })
        return root
    }
}