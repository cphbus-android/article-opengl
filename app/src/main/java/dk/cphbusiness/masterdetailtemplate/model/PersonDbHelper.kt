package dk.cphbusiness.masterdetailtemplate.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dk.cphbusiness.masterdetailtemplate.App
import org.jetbrains.anko.db.*
import java.util.*

class PersonDbHelper(context: Context = App.instance) : ManagedSQLiteOpenHelper(
        context,
        PersonDbHelper.DB_NAME,
        null,
        PersonDbHelper.DB_VERSION
        ) {

    companion object {
        val DB_NAME = "people.db"
        val DB_VERSION = 1
        val instance by lazy { PersonDbHelper() }
        }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
                PersonTable.NAME, true,
                PersonTable.ID to INTEGER + PRIMARY_KEY,
                PersonTable.FIRST_NAME to TEXT,
                PersonTable.LAST_NAME to TEXT,
                PersonTable.AGE to INTEGER
            )
        }

    override fun onUpgrade(
            db: SQLiteDatabase,
            oldVersion: Int,
            newVersion: Int
            ) {
        db.dropTable(PersonTable.NAME, true)
        onCreate(db)
        }

    }

class DbDataMapper

fun <T : Any> SelectQueryBuilder.parseList(
        parser: (Map<String, Any>) -> T
        ): List<T> =
    parseList(
            object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any>): T =
                        parser(columns)
        }
    )

fun <K, V : Any> MutableMap<K, V?>.toVarargArray():
        Array<out Pair<K, V>> =
            map({ Pair(it.key, it.value!!) }).toTypedArray()

class PersonDb (
        val helper : PersonDbHelper = PersonDbHelper.instance
        ) {

    fun requestPersonByMinAge(age: Int) = helper.use {
        val request = "${PersonTable.AGE} >= ?"
        select(PersonTable.NAME)
                .whereSimple(request, age.toString())
                .parseList { Person(HashMap(it)) }
        }

    fun savePerson(person: Person) = helper.use {
        insert(PersonTable.NAME, *person.map.toVarargArray() )
        }

    }


