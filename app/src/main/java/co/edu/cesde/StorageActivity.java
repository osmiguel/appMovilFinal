package co.edu.cesde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import co.edu.cesde.databinding.ActivityCreateProductBinding;
import co.edu.cesde.databinding.ActivitySignUpBinding;
import co.edu.cesde.databinding.ActivityStorageBinding;

public class StorageActivity extends AppCompatActivity {

    private ActivityStorageBinding storageBinding;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storageBinding = ActivityStorageBinding.inflate(getLayoutInflater());
        View view = storageBinding.getRoot();
        setContentView(view);
        dbHelper = new DBHelper(this);
    }

    public void getProduct(View view) {
        String codProduct = storageBinding.etCodPro.getText().toString();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = String.format("SELECT * FROM products WHERE codProduct='%s'",codProduct);
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        if (cursor.getCount() > 0) {
            String codPro = cursor.getString(1);
            String nomPro = cursor.getString(2);
            int pricePro = cursor.getInt(3);
            String stockPro = cursor.getString(4);
            storageBinding.txtCodProduct.setText("Cod Product: " + codPro);
            storageBinding.txtNomProduct.setText("Product: " + nomPro);
            storageBinding.txtPriceProduct.setText("Price: " + String.valueOf(pricePro) + "COP");
            storageBinding.txtStockPro.setText("Stock: " + stockPro);
            //Toast.makeText(this, "Price Product: " + priceProduct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "There are not products whit this COD", Toast.LENGTH_SHORT).show();
        }
    }
}