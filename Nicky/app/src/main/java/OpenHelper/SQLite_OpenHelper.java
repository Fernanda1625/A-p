package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nicky.MainActivity;

public class SQLite_OpenHelper extends SQLiteOpenHelper{
    public SQLite_OpenHelper(MainActivity mainActivity, String usuarios, Context context, int i){
        super(context, "usuarios", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table usuarios(_ID integer primary key autoincrement, "+
              "Nombre text, Email text, Celular number, Dirección text, Contraseña text);" ;
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //ABRIR BASE DE DATOS

    public void abrir(){
        this.getWritableDatabase();
    }
    //CERRAR BASE DE DATOS

    public void cerrar(){
        this.close();
    }

    //INSERTAR REGISTROS EN LA TABLA USUARIOS
    public void insertarRegistro(String nom, String email, Integer cel, String dir, String contr){
        ContentValues valores = new ContentValues();
        valores.put("Nombre", nom);
        valores.put("Email", email);
        valores.put("Celular", cel);
        valores.put("Dirección", dir);
        valores.put("Contraseña", contr);
        this.getWritableDatabase().insert("usuarios", null, valores);
    }

    //VALIDAR EXISTENCIA DEL USUARIO

    public Cursor ConsultarUsuContr(String usu, String contr)throws SQLException{
        Cursor cursor= null;
        cursor = this.getReadableDatabase().query("usuarios", new String[]{"_ID", "Nombre",
                        "Email", "Celular", "Dirección", "Contraseña"},
                "Email like '"+usu+"' "+" and Contraseña like '"+contr+"'",
                null, null, null, null, null);
        return cursor;
    }
}
