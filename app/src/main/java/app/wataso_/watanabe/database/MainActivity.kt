package app.wataso_.watanabe.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val memo: Memo? = read()

        if (memo !=null){
            titleEditText.setText(memo.title)
            contentEditText.setText(memo.content)
        }
        //保存ボタンを押した時に、save()メソッドにcontenctEditTextに入力した値を入れる
        saveButton.setOnClickListener{
            val title: String = titleEditText.text.toString()
            val content: String = contentEditText.text.toString()
            save(title,content)
        }

    }







    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun read(): Memo?{
        //Memoリストから最初のデータを取り出す
        return realm.where(Memo::class.java).findFirst()
    }

    fun save(title: String, content: String){

        val memo: Memo? = read()

        realm.executeTransaction{
            if (memo !=null){
                memo.title = title
                memo.content = content
            }else{
                //メモの新規作成
                val newMemo: Memo = it.createObject(Memo::class.java)
                newMemo.title = title
                newMemo.content = content
            }

            Snackbar.make(container,"保存しました！！",Snackbar.LENGTH_SHORT).show()
        }

    }
}