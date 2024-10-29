package com.example.danhsach
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.danhsach.R
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioPerfectSquare = findViewById<RadioButton>(R.id.radioPerfectSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val inputText = editTextNumber.text.toString()
            if (inputText.isBlank() || inputText.toIntOrNull() == null) {
                textViewError.text = "Vui lòng nhập một số nguyên dương"
                textViewError.visibility = TextView.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            val n = inputText.toInt()
            if (n <= 0) {
                textViewError.text = "Vui lòng nhập một số nguyên dương lớn hơn 0"
                textViewError.visibility = TextView.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            textViewError.visibility = TextView.GONE
            val resultList = when {
                radioEven.isChecked -> (0..n).filter { it % 2 == 0 }
                radioOdd.isChecked -> (1..n).filter { it % 2 != 0 }
                radioPerfectSquare.isChecked -> (0..n).filter { isPerfectSquare(it) }
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }

    private fun isPerfectSquare(number: Int): Boolean {
        val root = sqrt(number.toDouble()).toInt()
        return root * root == number
    }
}
