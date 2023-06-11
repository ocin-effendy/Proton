package com.example.proton.ui.managementDetailProduct

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proton.R
import com.example.proton.databinding.ActivityManagementDetailProductBinding
import com.example.proton.model.ProductModel
import com.example.proton.utils.DefaultFormat

@Suppress("DEPRECATION")
class ManagementDetailProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagementDetailProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagementDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            title = ""
        }

        val product = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(DATA_PRODUCT, ProductModel::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DATA_PRODUCT)
        }


        if(product != null){
            binding.date.text = DefaultFormat.getFormattedDate()
            binding.totalProfit.text = DefaultFormat.formatRupiah(product.price.toLong())

            binding.valueName.text = product.name
            binding.valueCode.text = product.code
            binding.valueStock.text = getString(R.string.value_stock, product.stock.toString())
            binding.valueCatergory.text = product.category
            binding.valueType.text = product.type
            binding.valueExpDate.text = product.dateExp
            binding.valuePrice.text = DefaultFormat.formatRupiah(product.price.toLong())
            binding.valueSellingPrice.text = DefaultFormat.formatRupiah(product.sellingPrice.toLong())
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    companion object {
        const val DATA_PRODUCT = "DATA_PRODUCT"
    }
}