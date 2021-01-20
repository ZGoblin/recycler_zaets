package com.example.recyclerzaets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerzaets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = getMe().treeToList(mutableListOf<Person>())

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = MyAdapter(items)
    }

    private fun Person.treeToList(list: MutableList<Person>, person: Person = this, startDistance: Int = 0) : MutableList<Person> {
        list.add(person)

        if (person.mother != null) treeToList(list, person.mother, startDistance + 1)
        if (person.father != null) treeToList(list, person.father, startDistance + 1)

        return list
    }

    private fun getMe(): Person {
        val greatGrandmotherBymotherOfMyMother = Person("Olga Lopatnichenko", 102, null, null)
        val greatGrandfatherBymotherOfMyMother = Person("Yarik Lopatnichenko", 99, null, null)
        val motherOfMyMother = Person("Sveta Lopatnichenko", 82, greatGrandmotherBymotherOfMyMother, greatGrandfatherBymotherOfMyMother)

        val greatGrandmotherByfatherOfMyMother = Person("Anna Lopatnichenko", 97, null, null)
        val greatGrandfatherByfatherOfMyMother = Person("Yaroslav Lopatnichenko", 98, null, null)
        val fatherOfMyMother = Person("Ivan Lopatnichenko", 88, greatGrandmotherByfatherOfMyMother, greatGrandfatherByfatherOfMyMother)

        val greatGrandmotherBymotherOfMyFather = Person("Julia Lopatnichenko", 97, null, null)
        val greatGrandfatherBymotherOfMyFather = Person("Andreii Lopatnichenko", 98, null, null)
        val motherOfMyFather = Person("Ludmila Zaets", 85, greatGrandmotherBymotherOfMyFather, greatGrandfatherBymotherOfMyFather)

        val greatGrandmotherByfatherOfMyFather = Person("Darina Lopatnichenko", 97, null, null)
        val greatGrandfatherByfatherOfMyFather = Person("Konstantin Lopatnichenko", 98, null, null)
        val fatherOfMyFather = Person("Petro Zaets", 90, greatGrandmotherByfatherOfMyFather, greatGrandfatherByfatherOfMyFather)

        val mother = Person("Lilia Zaets", 40, motherOfMyMother, fatherOfMyMother)
        val father = Person("Sergeii Zaets", 43, motherOfMyFather, fatherOfMyFather)

        val itsMe = Person("Oleg Zaets", 20, mother, father)

        return itsMe
    }
}