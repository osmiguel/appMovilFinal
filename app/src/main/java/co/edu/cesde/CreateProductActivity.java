package co.edu.cesde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import co.edu.cesde.databinding.ActivityCreateProductBinding;

public class CreateProductActivity extends AppCompatActivity {

    private ActivityCreateProductBinding createProductBinding;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createProductBinding = ActivityCreateProductBinding.inflate(getLayoutInflater());
        View view = createProductBinding.getRoot();
        setContentView(view);
        dbHelper = new DBHelper(this);
    }

    public void storage(View view) {
        Intent intent = new Intent(CreateProductActivity.this, StorageActivity.class);
        startActivity(intent);
    }

    public void createProduct(View view) {
        String codProd = createProductBinding.etNomProduct.getText().toString();
        String namProd = createProductBinding.etNomProduct.getText().toString();
        String priceProd = createProductBinding.etNomProduct.getText().toString();
        String stockProd = createProductBinding.etStock.getText().toString();
        if(codProd.isEmpty() || namProd.isEmpty() || priceProd.isEmpty() || stockProd.isEmpty()) {
            Toast.makeText(this, "All dates are required", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase dbProduct = dbHelper.getWritableDatabase();
            ContentValues productData = new ContentValues();
            String codProduct = createProductBinding.etCodProduct.getText().toString();
            String nomProduct = createProductBinding.etNomProduct.getText().toString();
            String priceProduct = createProductBinding.etPrice.getText().toString();
            String stock = createProductBinding.etStock.getText().toString();
            productData.put("codProduct", codProduct);
            productData.put("nomProduct", nomProduct);
            productData.put("priceProduct", priceProduct);
            productData.put("stock", stock);
            long newProduct = dbProduct.insert("products", null, productData);
            Toast.makeText(this, "Created Product COD: " + codProduct, Toast.LENGTH_SHORT).show();
        }
    }
}