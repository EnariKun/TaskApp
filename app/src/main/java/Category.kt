package jp.techacademy.manao.taskapp

import java.io.Serializable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category : RealmObject(), Serializable {
    var categoryName: String = ""   //カテゴリ名

    // id をプライマリーキーとして設定
    @PrimaryKey
    var id: Int = 0
}