package com.example.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.example.storage.database.AppDatabase
import com.example.storage.database.Car
import com.example.storage.database.Person
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            AppDatabase.getDatabase(this@MainActivity).personDao().insertAll(
                Person(1, "Erekle"),
                Person(2, "Tengizi"),
                Person(3, "Apoloni")
            )

            AppDatabase.getDatabase(this@MainActivity).carDao().insertAll(
                Car(1, 1, "BMW"),
                Car(2, 3, "Mercedes"),
                Car(3, 2, "Volvo"),
                Car(4, 3, "Mustang"),
                Car(5, 1, "Ferrari"),
                Car(6, 3, "Chevron"),
                Car(7, 1, "Bugatti"),
            )

            d(
                "TestLog",
                "${AppDatabase.getDatabase(this@MainActivity).personDao().getPersonsWithCars()}"
            )
        }

        searchButton.setOnClickListener {
            val person = personEditText.text.toString()

            GlobalScope.launch(Dispatchers.IO) {
                val personCars = AppDatabase.getDatabase(this@MainActivity).personDao()
                    .getCarsByPersonName(person)

                personCars.forEach {
                    it.cars.forEach { car ->
                        d("TestLog", car.carName)
                    }
                }
            }
        }
    }
}
