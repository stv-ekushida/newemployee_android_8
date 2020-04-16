# newemployee_android_8
オプションメニューとコンテキストメニュー

## オプションメニュー

### レイアウト
res/menu/menu_options_menu_list.xml

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item android:id="@+id/menuListOptionTeishoku"
        app:showAsAction="never"
        android:title="@string/menu_list_options_teishoku"/>

    <item android:id="@+id/menuListOptionCurry"
        app:showAsAction="never"
        android:title="@string/menu_list_options_curry"/>
</menu>
```
 | 属性値 | 内容 |
 |---|---|
 |never | オーバフローメニューに格納される |
 |always | アクションバーに表示される |
 |ifRoom | アクションバーに余裕があれば表示し、なければ、オーバフローメニューに格納される |


### ソースコード

```
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
        return super.onOptionsItemSelected(item)
    }
```

## コンテキストメニュー

### レイアウト
res/menu/menu_context_menu_list.xml

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:id="@+id/menuListContextDesc"
        android:title="@string/menu_list_context_desc"/>

    <item android:id="@+id/menuListContextOrder"
        android:title="@string/menu_list_context_order"/>
</menu>
```

### ソースコード
```
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
```    


