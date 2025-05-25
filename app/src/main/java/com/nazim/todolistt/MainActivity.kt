package com.nazim.todolistt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val toDoList = ArrayList<ModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText = findViewById<EditText>(R.id.editTextText)
        val button = findViewById<Button>(R.id.button);
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        val adapter = ToDoAdapter(toDoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        button.isEnabled = false


        button.setOnClickListener {
            button.isEnabled = false
            toDoList.add(ModelClass(toDoList.size + 1, editText.text.toString(), false))
            adapter.notifyItemInserted(toDoList.size)
            editText.text.clear()
        }
        editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {}

            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = !s.isNullOrEmpty()
            }

        })



    }
}