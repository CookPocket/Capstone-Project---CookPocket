package com.capstone.cookpocket.view.ui.account.change

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.capstone.cookpocket.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ChangeAddresActivity : AppCompatActivity() {

    private lateinit var edChangeAddres: TextInputLayout
    private lateinit var edChangeKelurahan: TextInputLayout
    private lateinit var edChangeKecamatan: TextInputLayout
    private lateinit var edChangeKabupaten: TextInputLayout
    private lateinit var edChangeProvinsi: TextInputLayout

    private lateinit var btnSimpanAlamat: Button
    private lateinit var progressBarAlamat: ProgressBar
    private lateinit var ivBackAlamat: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_addres)

        // Inisialisasi komponen UI
        edChangeAddres = findViewById(R.id.ed_change_addres)
        edChangeKelurahan = findViewById(R.id.ed_change_kelurahan)
        edChangeKecamatan = findViewById(R.id.ed_change_kecamatan)
        edChangeKabupaten = findViewById(R.id.ed_change_kabupaten)
        edChangeProvinsi = findViewById(R.id.ed_change_provinsi)

        btnSimpanAlamat = findViewById(R.id.btn_simpan_password)
        progressBarAlamat = findViewById(R.id.progresbar_alamat)
        ivBackAlamat = findViewById(R.id.iv_back_addres)

        // Tombol kembali
        ivBackAlamat.setOnClickListener { finish() }

        // Tombol simpan
        btnSimpanAlamat.setOnClickListener {
            handleSaveAddress()
        }
    }

    private fun handleSaveAddress() {
        val address = edChangeAddres.editText?.text.toString()
        val kelurahan = edChangeKelurahan.editText?.text.toString()
        val kecamatan = edChangeKecamatan.editText?.text.toString()
        val kabupaten = edChangeKabupaten.editText?.text.toString()
        val provinsi = edChangeProvinsi.editText?.text.toString()

        // Validasi input
        if (validateInputs(address, kelurahan, kecamatan, kabupaten, provinsi)) {
            showConfirmationDialog(address, kelurahan, kecamatan, kabupaten, provinsi)
        }
    }

    private fun validateInputs(
        address: String,
        kelurahan: String,
        kecamatan: String,
        kabupaten: String,
        provinsi: String
    ): Boolean {
        var isValid = true

        if (address.isEmpty()) {
            edChangeAddres.error = getString(R.string.error_empty_address)
            isValid = false
        } else {
            edChangeAddres.error = null
        }

        if (kelurahan.isEmpty()) {
            edChangeKelurahan.error = getString(R.string.error_empty_kelurahan)
            isValid = false
        } else {
            edChangeKelurahan.error = null
        }

        if (kecamatan.isEmpty()) {
            edChangeKecamatan.error = getString(R.string.error_empty_kecamatan)
            isValid = false
        } else {
            edChangeKecamatan.error = null
        }

        if (kabupaten.isEmpty()) {
            edChangeKabupaten.error = getString(R.string.error_empty_kabupaten)
            isValid = false
        } else {
            edChangeKabupaten.error = null
        }

        if (provinsi.isEmpty()) {
            edChangeProvinsi.error = getString(R.string.error_empty_provinsi)
            isValid = false
        } else {
            edChangeProvinsi.error = null
        }

        return isValid
    }

    private fun showConfirmationDialog(
        address: String,
        kelurahan: String,
        kecamatan: String,
        kabupaten: String,
        provinsi: String
    ) {
        val fullAddress = "$address, $kelurahan, $kecamatan, $kabupaten, $provinsi"
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirmation))
            .setMessage(getString(R.string.confirm_save_address))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                saveAddress(fullAddress)
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun saveAddress(fullAddress: String) {
        progressBarAlamat.visibility = View.VISIBLE

        // Simulasi penyimpanan data (dapat diganti dengan penyimpanan SharedPreferences atau API)
        progressBarAlamat.postDelayed({
            progressBarAlamat.visibility = View.GONE
            Toast.makeText(
                this,
                getString(R.string.success_save_address),
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }, 1500)
    }
}
