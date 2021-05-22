package app.wataso_.watanabe.database

import io.realm.RealmObject

open class Memo (
    open var title: String ="",
    open var content: String = ""
) : RealmObject()