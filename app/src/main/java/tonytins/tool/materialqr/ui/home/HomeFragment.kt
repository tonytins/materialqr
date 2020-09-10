/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package tonytins.tool.materialqr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tonytins.tool.materialqr.R
import tonytins.tool.materialqr.common.QrGenerator

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
                val qrGen = QrGenerator(text)
                val previewImg = qrGen.generate(1000,1000)

                qrPreview.setImageBitmap(previewImg)
            }
        })
        return root
    }
}