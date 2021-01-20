package com.example.recyclerzaets

class Person {
    val name: String
    val age: Int
    val mother: Person?
    val father: Person?
    var siblings: HashSet<Person> = hashSetOf<Person>()
    var numberOfRelatives: Int = 0

    constructor(name: String, age: Int, mother: Person?, father: Person?) {
        this.name = name
        this.age = age
        this.mother = mother
        this.father = father
        reloadNumberOfRelatives()
    }

    fun addSiblings(person: Person) {
        siblings.add(person)
    }

    fun reloadNumberOfRelatives() {
        numberOfRelatives = 0
        findAllRelatives()
        rewriteNumberOfRelatives(numberOfRelatives)
    }

    override fun toString(): String {
        return StringBuilder()
                .append(name)
                .append(" ")
                .append(age)
                .toString()
    }

    private fun findAllRelatives(person: Person = this) {
        numberOfRelatives++
        numberOfRelatives += person.siblings.size

        if (person.mother != null) findAllRelatives(person.mother)
        if (person.father != null) findAllRelatives(person.father)
    }

    private fun rewriteNumberOfRelatives(numberOfRelatives: Int, person: Person = this) {
        person.numberOfRelatives = numberOfRelatives
        siblings.forEach {
            it.numberOfRelatives = numberOfRelatives
        }

        if (person.mother != null) rewriteNumberOfRelatives(numberOfRelatives, person.mother)
        if (person.mother != null) rewriteNumberOfRelatives(numberOfRelatives, person.mother)
    }
}