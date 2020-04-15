package jp.co.stv_tech.android_8_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var _menuList: MutableList<MutableMap<String, Any>>? = null
    private var FROM = arrayOf("name","price")
    private var TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifeCycle", "Main OnCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)

        _menuList = createTeishokuList()

        val adapter = SimpleAdapter(applicationContext,
                _menuList,
                R.layout.row,
                FROM,
                TO)
        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListner()

        registerForContextMenu(lvMenu)
    }

    public override fun onStart() {
        Log.i("LifeCycle", "Main onStart called")
        super.onStart()
    }

    public override fun onRestart() {
        Log.i("LifeCycle", "Main onReStart called")
        super.onRestart()
    }

    public override fun onResume() {
        Log.i("LifeCycle", "Main onResume called")
        super.onResume()
    }

    public override fun onPause() {
        Log.i("LifeCycle", "Main onPause called")
        super.onPause()
    }

    public override fun onStop() {
        Log.i("LifeCycle", "Main onStop called")
        super.onStop()
    }

    public override fun onDestroy() {
        Log.i("LifeCycle", "Main onDestory called")
        super.onDestroy()
    }

    private inner class ListItemClickListner : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>,
                                 view: View?,
                                 position: Int,
                                 id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            order(item)
        }
    }

    private fun createTeishokuList() : MutableList<MutableMap<String, Any>> {

        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menu = mutableMapOf<String, Any>("name" to "唐揚げ定食",
            "price" to 800,
            "desc" to "唐揚げ定食にサラダ、ご飯とお味噌汁がつきます")
        menuList.add(menu)

        menu = mutableMapOf<String, Any>("name" to "ハンバーグ定食",
            "price" to 850,
            "desc" to "ハンバーグ定食にサラダ、ご飯とお味噌汁がつきます")
        menuList.add(menu)

        return menuList
    }

    private fun createCurryList() : MutableList<MutableMap<String, Any>> {

        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()

        var menu = mutableMapOf<String, Any>("name" to "ビーフカレー",
            "price" to 520,
            "desc" to "特選スパイスをきかせた国産ビーフカレーです。")
        menuList.add(menu)

        menu = mutableMapOf<String, Any>("name" to "ポークカレー",
            "price" to 420,
            "desc" to "特選スパイスをきかせた国産ポークカレーです。")
        menuList.add(menu)

        return menuList
    }

    //オプションメニューの生成
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //オプションメニューが選択されたとき
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menuListOptionTeishoku -> {
                _menuList = createTeishokuList()
            }
            R.id.menuListOptionCurry -> {
                _menuList = createCurryList()
            }
        }
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(
            applicationContext,
            _menuList,
            R.layout.row,
            FROM,
            TO
        )
        lvMenu.adapter = adapter
        return super.onOptionsItemSelected(item)
    }

    //コンテキストメニューの作成
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_context_menu_list, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    //コンテキストメニューを選択されたとき
    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPostion = info.position
        val menu = _menuList!![listPostion]

        when(item.itemId) {
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(applicationContext, desc, Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder -> {
                order(menu)
            }
        }
        return super.onContextItemSelected(item)
    }


    private fun order(menu: MutableMap<String, Any>) {

        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int

        val intent = Intent(applicationContext, MenuThanksActivity::class.java)
        intent.putExtra("menuName", menuName)
        intent.putExtra("menuPrice", "${menuPrice}")
        startActivity(intent)
    }
}
