package jp.techacademy.manao.taskapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.content_category.*

class CategoryActivity : AppCompatActivity() {

    private var mCategory: Category? = null

    private val mOnAddClickListener = View.OnClickListener {
        if(categoryName_edit_text.length() != 0) {
            addCategory()
            finish()
        } else {
            Snackbar.make(it, "カテゴリ名を入力してください", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // ActionBarを設定する
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        // UI部品の設定
        add_button.setOnClickListener(mOnAddClickListener)
    }

    private fun addCategory() {

        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()
        // 新規作成
        mCategory = Category()

        val categoryRealmResults = realm.where(Category::class.java).findAll()

        val identifier: Int =
            if (categoryRealmResults.max("id") != null) {
                categoryRealmResults.max("id")!!.toInt() + 1
            } else {
                0
            }
        mCategory!!.id = identifier

        val categoryName = categoryName_edit_text.text.toString()

        mCategory!!.categoryName = categoryName

        realm.copyToRealmOrUpdate(mCategory!!)
        realm.commitTransaction()

        realm.close()

    }
}