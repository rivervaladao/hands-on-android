## Passo 03
* Alterando style.xml para ocultar ActionBar e usar ToolBar 

1 - Criar diretorio res/values-21 e editar style.xml em res/values e res/values-21
 
```xml
<resources>
    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>

    <style name="AppTheme.AppBarOverlay"
        parent="ThemeOverlay.AppCompat.Dark.ActionBar"/>

    <style name="AppTheme.PopupOverlay"
        parent="ThemeOverlay.AppCompat.Light"/>
</resources>
```

* Adcionando toolbar para apps com suporte  
Toolbar toolbar;
 
```xml
 <android.support.v7.widget.Toolbar xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/primary"
        android:minHeight="?android:attr/actionBarSize"
        android:transitionName="tActionBar"/>
```

* configurando na activity
         
```java
private void initInstances() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar); // compatibilidade setActionBar -> setSupportActionBar
    ...
}
```

## Passo 02

 * Adicionar layout `res/layout/fragment_detail_tarefa.xml`
 
 * Observar atibuto `android:background=?android:selectableItemBackground` cria efeito onda ao clicar
 
 ```xml
     <LinearLayout
         android:id="@+id/tarefaRowHolder"
         android:background="?android:selectableItemBackground">

     </LinearLayout>
```

 * Adicionar manipulador de evento para click no item de tarefa, modificar `TarefaListAdapter` e adicionar
 interface 
 
 ```java
     //1
     private OnItemClickListener mItemClickListener;
     //2
     public interface OnItemClickListener {
         void onItemClick(View view, int position);
     }
     //3
     public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
         this.mItemClickListener = mItemClickListener;
     }
     //4
     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {  
        //5
        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                 mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
        
     }
     //6
     public ViewHolder(View itemView) {
         super(itemView);

         tarefaHolder.setOnClickListener(this);
     }
 ```
 
 * Usando fragmento para mostrar detalhe ao clicar no item da tarefa `res/layout/fragment_detail_tarefa.xml`
 
 ```java
    TarefaListAdapter.OnItemClickListener onItemClickListener = new TarefaListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (savedInstanceState == null) {
                final Tarefa tarefa = DBTarefas.tarefaList().get(position);

                final TarefaDetailFragment tarefaDetailFragment = TarefaDetailFragment.newInstance(MainActivity.this,tarefa);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_layout, tarefaDetailFragment, "tarefaDeatailFragment")
                        .addToBackStack(null)
                        .commit();
            }
        }
    };
 ```
 
 * Usar fragmentos ao inves de Activity, [REUSO, MODURALIDADE, ADAPTABILIDADE]
 
 ```java
 public class TarefaDetailFragment extends Fragment {
     //Obrigatorio para FragmentManager
     public TarefaDetailFragment() {
     }
     //padrao factory
     public static TarefaDetailFragment newInstance(){
     ....
         return fragment;
     }

 }
 ```
## Passo 01  
RecyclerView, Adpter e Material Design (SDK >= 21)

* Adicionar dependencias em  `build.gradle`

```gradle
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.+'
    compile 'com.android.support:recyclerview-v7:23.+'
    compile 'com.android.support:cardview-v7:23.+'
    compile 'com.android.support:design:23.+'
}
```
* Configurar manisfesto `manifest/AndroidManifest.xml` , definir `android:theme="@style/Theme.AppCompat"`, compatibilidade com SDK.21 

```xml
 <application
         android:allowBackup="true"
         android:icon="@mipmap/ic_launcher"
         android:label="@string/app_name"
         android:supportsRtl="true"
         android:theme="@style/Theme.AppCompat">
         <activity
             android:name=".MainActivity"
             android:label="@string/app_name">
             <intent-filter>
                 <action android:name="android.intent.action.MAIN"/>
 
                 <category android:name="android.intent.category.LAUNCHER"/>
             </intent-filter>
         </activity>
     </application>
```   
* Adicionar definições de cores em `values/colors.xml`

```xml
<resources>
    <color name="primary">#FFFFFF</color>
    <color name="primary_dark">#6C7F91</color>
    <color name="accent">#6C7F91</color>

    <color name="light_green">#7ABA34</color>
    <color name="dark_green">#5B852D</color>

    <color name="light_blue">#66b3ff</color>
    <color name="dark_blue">#004d99</color>

    <color name="light_red">#ff6666</color>
    <color name="dark_red">#ff0000</color>

    <color name="light_yellow">#ffff66</color>
    <color name="dark_yellow">#b3b300</color>

    <color name="light_orange">#ffa31a</color>
    <color name="dark_orange">#cc2900</color>

    <color name="light_gray">#F2F2F2</color>
    <color name="photo_tint">#7f101010</color>
</resources>
```
* Adicionar definição de dimensões

```xml
<resources>
    <!-- Default screen margins, per the Android Design guidelines. -->
    <dimen name="activity_horizontal_margin">16dp</dimen>
    <dimen name="activity_vertical_margin">16dp</dimen>

    <dimen name="fab_button_margin_bottom">16dp</dimen>
    <dimen name="fab_button_margin_right">16dp</dimen>

    <dimen name="floating_button_size">60dp</dimen>
    <dimen name="button_elevation">1dp</dimen>
    <dimen name="button_press_elevation">18dp</dimen>
    <dimen name="card_elevation">1dp</dimen>
    <dimen name="card_corner_radius">5dp</dimen>
</resources>
```


* Adicionar RecyclerView no `main_activity.xml` layout

```xml
  <android.support.v7.widget.RecyclerView
      android:id="@+id/list"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:background="@color/light_gray"/>
```
* criar resouce layout/drawable para icones do botão flutuante
`icon_add.xml`

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="30dp"
    android:height="30dp"
    android:viewportWidth="30"
    android:viewportHeight="30">

    <group>
        <path
            android:name="sm_vertical_line"
            android:strokeColor="@android:color/white"
            android:strokeWidth="3"
            android:pathData="M14.0132576,0.212121212 L14.0132576,7.84848485" />
        <path
            android:name="lg_vertical_line"
            android:strokeColor="@android:color/white"
            android:strokeWidth="3"
            android:pathData="M14.0132576,8 L14.0132576,27.7878788" />
        <path
            android:name="horizontal_line"
            android:strokeColor="@android:color/background_light"
            android:strokeWidth="3"
            android:pathData="M0,14.4375 L28,14.4375" />
    </group>

</vector>
```

`icone_done.xml`
```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="30dp"
    android:height="30dp"
    android:viewportWidth="30"
    android:viewportHeight="30">

    <group>
        <path
            android:name="sm_vertical_line"
            android:strokeColor="@android:color/white"
            android:strokeWidth="3"
            android:pathData="M2,10.70640746 L9.84484711,23.9" />
        <path
            android:name="lg_vertical_line"
            android:strokeColor="@android:color/white"
            android:strokeWidth="3"
            android:pathData="M9.5,22.5 L31,6.5" />
    </group>

</vector>
```

* Criando row_resumo_tarefa.xml layout com CardView
adicionando `xmlns:card_view="http://schemas.android.com/apk/res-auto"`, 
podemos definir atributos como  ``card_view:cardCornerRadius`` e ``card_view:cardElevation``
 
 que são responsáveis por habilitar a aparência dos Cards nas app android dada pelo Material Design.
 
* Alterar layout activity_main.xml : 
 
 ```xml
   <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
     xmlns:tools="http://schemas.android.com/tools"
     xmlns:app="http://schemas.android.com/apk/res-auto"
     android:layout_width="match_parent"
     android:layout_height="match_parent"
     tools:context="com.river.app.MainActivity">
 
     <android.support.v7.widget.RecyclerView
         android:id="@+id/list"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:background="@color/light_gray" />
 
     <android.support.design.widget.FloatingActionButton
         android:id="@+id/fab"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:src="@drawable/icon_add"
         android:layout_alignParentRight="true"
         android:layout_alignParentBottom="true"
         android:layout_marginBottom="@dimen/fab_button_margin_bottom"
         android:layout_marginRight="@dimen/fab_button_margin_right"
         app:elevation="6dp"
         app:pressedTranslationZ="12dp"
         app:borderWidth="0dp"/>
 </RelativeLayout>
 ```

* adicionar classe adpatadora e viewholder pattern, para RecycleView

* associar MainActivity ao RecyclerView

