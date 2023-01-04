package ___PACKAGE_NAME___.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
	entities = [/* add entities*/],
	version = ___APP_NAME_CAMEL___Database.VERSION,
	exportSchema = false
)
abstract class ___APP_NAME_CAMEL___Database : RoomDatabase() {

	companion object {
		const val NAME = "___APP_NAME_CAMEL___Database"
		const val VERSION = 2
	}

	//    abstract fun exampleDao(): ExampleDao
}
