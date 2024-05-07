package com.example.listacompras.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listacompras.viewmodel.ItemsViewModel
import com.example.listacompras.viewmodel.ItemsViewModelFactory
import com.example.listacompras.R

class MainActivity : AppCompatActivity() {
    private val viewModel: ItemsViewModel by viewModels {
        ItemsViewModelFactory(applicationContext)
    };

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView);
        val itemsAdapter = ItemsAdapter();

        recyclerView.adapter = itemsAdapter;

        val button = findViewById<Button>(R.id.button);
        val editText = findViewById<EditText>(R.id.editText);

        button.setOnClickListener(){
            val name = editText.text.toString()
            if(name.isEmpty()){
                editText.error ="Preencha com um valor"
                return@setOnClickListener;
            }
           viewModel.addItem(editText.text.toString())
            editText.text.clear();
        }
        viewModel.itemsLiveData.observe(this) {
            items -> itemsAdapter.updateItems((items))
        }
    }
}