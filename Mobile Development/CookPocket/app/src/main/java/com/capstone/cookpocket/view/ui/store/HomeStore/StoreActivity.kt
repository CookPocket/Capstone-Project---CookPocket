//package com.capstone.cookpocket.view.ui.store.HomeStore
//
//import android.os.Bundle
//import android.view.View
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.capstone.cookpocket.databinding.ActivityStoreBinding
//import com.capstone.cookpocket.view.ui.adapter.AdapterAddProductActivity
//
//class StoreActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityStoreBinding
//    private val storeViewModel: StoreViewModel by viewModels { StoreViewModelFactory(this) }
//    private lateinit var adapter: AdapterAddProductActivity
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityStoreBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        adapter = AdapterAddProductActivity { story, imageView, textView ->
//            // Handle item click here
//        }
//
//        binding.rvStore.layoutManager = LinearLayoutManager(this)
//        binding.rvStore.adapter = adapter
//
//        storeViewModel.stories.observe(this) { stories ->
//            adapter.submitList(stories)
//        }
//
//        storeViewModel.isLoading.observe(this) { isLoading ->
//            // Show/hide loading indicator
////            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//        }
//
//        storeViewModel.errorMessage.observe(this) { errorMessage ->
//            // Show error message if needed
//            errorMessage?.let {
//                // Display a toast or a dialog to show the error
//            }
//        }
//
//        storeViewModel.fetchStories()
//    }
//}
