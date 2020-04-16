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
```
