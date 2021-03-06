package com.inkostilaton.dbpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.inkostilaton.dbpractice.MainActivity.database;

public class Database extends SQLiteOpenHelper {

    public static final String CUSTOMER = "CUSTOMER";
    public static final String CUSTOMER_COLUMN_CUST_ID = "CUST_ID";
    public static final String CUSTOMER_COLUMN_NAME = "NAME";
    public static final String CUSTOMER_COLUMN_POSTAL = "POSTAL";
    public static final String CUSTOMER_COLUMN_STATE = "STATE";
    public static final String CUSTOMER_COLUMN_CITY = "CITY";
    public static final String CUSTOMER_COLUMN_ADDRESS = "ADDRESS";

    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String EMPLOYEE_COLUMN_EMP_ID = "EMP_ID";
    public static final String EMPLOYEE_COLUMN_NAME = "NAME";
    public static final String EMPLOYEE_COLUMN_TITLE = "TITLE";
    public static final String EMPLOYEE_COLUMN_DEPARTMENT = "DEPARTMENT";
    public static final String EMPLOYEE_COLUMN_OFFICE = "OFFICE";
    public static final String EMPLOYEE_COLUMN_SUPERIOR = "SUPERIOR";
    public static final String EMPLOYEE_COLUMN_START_DATE = "START_DATE";
    public static final String EMPLOYEE_COLUMN_END_DATE = "END_DATE";

    public static final String DEPARTMENT = "DEPARTMENT";
    public static final String DEPARTMENT_COLUMN_NAME = "NAME";

    public static final String OFFICE = "OFFICE";
    public static final String OFFICE_COLUMN_ADDRESS = "ADDRESS";

    public static final String PRODUCT = "PRODUCT";
    public static final String PRODUCT_COLUMN_PROD_ID = "PROD_ID";
    public static final String PRODUCT_COLUMN_NAME = "NAME";
    public static final String PRODUCT_COLUMN_TYPE = "TYPE";
    public static final String PRODUCT_COLUMN_START_DATE = "START_DATE";
    public static final String PRODUCT_COLUMN_END_DATE = "END_DATE";

    public static final String PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String PRODUCT_TYPE_COLUMN_NAME = "NAME";

    public static final String ORDERS = "ORDERS";
    public static final String ORDER_COLUMN_ORDER_ID = "ORDER_ID";
    public static final String ORDER_COLUMN_CUSTOMER = "CUSTOMER";
    public static final String ORDER_COLUMN_STATUS = "STATUS";
    public static final String ORDER_COLUMN_EMPLOYEE = "EMPLOYEE";
    public static final String ORDER_COLUMN_START_DATE = "START_DATE";
    public static final String ORDER_COLUMN_END_DATE = "END_DATE";
    public static final String ORDER_COLUMN_SUM = "SUM";

    public static final String TRANSACTIONS = "TRANSACTIONS";
    public static final String TRANSACTIONS_COLUMN_TR_ID = "TR_ID";
    public static final String TRANSACTIONS_COLUMN_DATE = "TR_DATE";
    public static final String TRANSACTIONS_COLUMN_VALUE = "VALUE";
    public static final String TRANSACTIONS_COLUMN_ORDER_ID = "ORDER_ID";

    public static final String PRODUCT_LIST = "PRODUCT_LIST";
    public static final String PRODUCT_LIST_COLUMN_PROD_ID = "PROD_IN_LIST_ID";
    public static final String PRODUCT_LIST_COLUMN_ORDER_ID = "ORDER_ID";
    public static final String PRODUCT_LIST_COLUMN_PRODUCT_NAME = "PRODUCT_NAME";

    private int deletedCustomers = 0;
    private int deletedProducts = 0;
    private int deletedEmployees = 0;
    private int deletedOrders = 0;

    public Database(@Nullable Context context) {
        super(context, "base.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCustomerTable = "CREATE TABLE " + CUSTOMER + " (" + CUSTOMER_COLUMN_CUST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_COLUMN_NAME + " TEXT, " + CUSTOMER_COLUMN_POSTAL + " TEXT, " + CUSTOMER_COLUMN_STATE + " TEXT, " + CUSTOMER_COLUMN_CITY + " TEXT, " + CUSTOMER_COLUMN_ADDRESS + " TEXT)";
        db.execSQL(createCustomerTable);
        String createEmployeeTable = "CREATE TABLE " + EMPLOYEE + " (" + EMPLOYEE_COLUMN_EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMPLOYEE_COLUMN_NAME + " TEXT, " + EMPLOYEE_COLUMN_TITLE + " TEXT, " + EMPLOYEE_COLUMN_DEPARTMENT + " TEXT, " + EMPLOYEE_COLUMN_OFFICE + " TEXT, " + EMPLOYEE_COLUMN_SUPERIOR + " TEXT, " + EMPLOYEE_COLUMN_START_DATE + " TEXT, " + EMPLOYEE_COLUMN_END_DATE + " TEXT)";
        db.execSQL(createEmployeeTable);
        String createDepartmentTable = "CREATE TABLE " + DEPARTMENT + " (" + DEPARTMENT_COLUMN_NAME + " TEXT)";
        db.execSQL(createDepartmentTable);
        String createOfficeTable = "CREATE TABLE " + OFFICE + " (" + OFFICE_COLUMN_ADDRESS + " TEXT)";
        db.execSQL(createOfficeTable);
        String createProductTable = "CREATE TABLE " + PRODUCT + " (" + PRODUCT_COLUMN_PROD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_COLUMN_NAME + " TEXT, " + PRODUCT_COLUMN_TYPE + " TEXT, " + PRODUCT_COLUMN_START_DATE + " TEXT, " + PRODUCT_COLUMN_END_DATE + " TEXT)";
        db.execSQL(createProductTable);
        String createProductTypeTable = "CREATE TABLE " + PRODUCT_TYPE + " (" + PRODUCT_TYPE_COLUMN_NAME + " TEXT)";
        db.execSQL(createProductTypeTable);
        String createOrderTable = "CREATE TABLE " + ORDERS + " (" + ORDER_COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORDER_COLUMN_CUSTOMER + " TEXT, " + ORDER_COLUMN_STATUS + " TEXT, " + ORDER_COLUMN_EMPLOYEE + " TEXT, " + ORDER_COLUMN_START_DATE + " TEXT, " + ORDER_COLUMN_END_DATE + " TEXT, " + ORDER_COLUMN_SUM + " TEXT)";
        db.execSQL(createOrderTable);
        String createTransactionTable = "CREATE TABLE " + TRANSACTIONS + " (" + TRANSACTIONS_COLUMN_TR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TRANSACTIONS_COLUMN_DATE + " TEXT, " + TRANSACTIONS_COLUMN_VALUE + " INT, " + TRANSACTIONS_COLUMN_ORDER_ID + " INT)";
        db.execSQL(createTransactionTable);
        String createProductListTable = "CREATE TABLE " + PRODUCT_LIST + " (" + PRODUCT_LIST_COLUMN_PROD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_LIST_COLUMN_ORDER_ID + " TEXT, " + PRODUCT_LIST_COLUMN_PRODUCT_NAME + " TEXT)";
        db.execSQL(createProductListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + OFFICE);
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_LIST);
        onCreate(db);

    }

    public int getDeletedCustomers() {
        return deletedCustomers;
    }
    public int getDeletedProducts() {
        return deletedProducts;
    }
    public int getDeletedEmployees() {
        return deletedEmployees;
    }
    public int getDeletedOrders() {
        return deletedOrders;
    }

    public void addCustomer(CustomModel customModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_COLUMN_NAME, customModel.getName());
        cv.put(CUSTOMER_COLUMN_POSTAL, customModel.getPostal());
        cv.put(CUSTOMER_COLUMN_STATE, customModel.getState());
        cv.put(CUSTOMER_COLUMN_CITY, customModel.getCity());
        cv.put(CUSTOMER_COLUMN_ADDRESS, customModel.getAddress());

        db.insert(CUSTOMER, null, cv);
    }

    public void addEmployee(EmpModel empModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EMPLOYEE_COLUMN_NAME, empModel.getName());
        cv.put(EMPLOYEE_COLUMN_TITLE, empModel.getTitle());
        cv.put(EMPLOYEE_COLUMN_DEPARTMENT, empModel.getDepartment());
        cv.put(EMPLOYEE_COLUMN_OFFICE, empModel.getOffice());
        cv.put(EMPLOYEE_COLUMN_SUPERIOR, empModel.getSuperior());
        cv.put(EMPLOYEE_COLUMN_START_DATE, empModel.getStartDate());
        cv.put(EMPLOYEE_COLUMN_END_DATE, empModel.getEndDate());

        db.insert(EMPLOYEE, null, cv);
    }

    public void addDepartment(DepartmentModel departmentModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DEPARTMENT_COLUMN_NAME, departmentModel.getName());

        db.insert(DEPARTMENT, null, cv);
    }

    public void addOffice(OfficeModel officeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(OFFICE_COLUMN_ADDRESS, officeModel.getAddress());

        db.insert(OFFICE, null, cv);
    }

    public void addProduct(ProductModel productModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_COLUMN_NAME, productModel.getName());
        cv.put(PRODUCT_COLUMN_TYPE, productModel.getType());
        cv.put(PRODUCT_COLUMN_START_DATE, productModel.getStartDate());
        cv.put(PRODUCT_COLUMN_END_DATE, productModel.getEndDate());

        db.insert(PRODUCT, null, cv);
    }

    public void addProductType(ProductTypeModel productTypeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_TYPE_COLUMN_NAME, productTypeModel.getName());

        db.insert(PRODUCT_TYPE, null, cv);
    }

    public void addOrder(OrderModel orderModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ORDER_COLUMN_ORDER_ID, orderModel.getOrder_id());
        cv.put(ORDER_COLUMN_CUSTOMER, orderModel.getCustomer());
        cv.put(ORDER_COLUMN_STATUS, orderModel.getStatus());
        cv.put(ORDER_COLUMN_EMPLOYEE, orderModel.getEmp());
        cv.put(ORDER_COLUMN_START_DATE, orderModel.getStartDate());
        cv.put(ORDER_COLUMN_END_DATE, orderModel.getEndDate());
        cv.put(ORDER_COLUMN_SUM, orderModel.getSum());

        db.insert(ORDERS, null, cv);
    }

    public void addTransaction(TransactionModel transactionModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TRANSACTIONS_COLUMN_VALUE, transactionModel.getValue());
        cv.put(TRANSACTIONS_COLUMN_DATE, transactionModel.getDate());
        cv.put(TRANSACTIONS_COLUMN_ORDER_ID, transactionModel.getOrder_id());

        db.insert(TRANSACTIONS, null, cv);
    }

    public void addProductList(ProductListModel productListModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_LIST_COLUMN_ORDER_ID, productListModel.getOrder_id());
        cv.put(PRODUCT_LIST_COLUMN_PRODUCT_NAME, productListModel.getProduct_name());

        db.insert(PRODUCT_LIST, null, cv);
    }


    public List<String> getEmployeesNames() {
        ArrayList<String> employeesNames = new ArrayList<>();
        employeesNames.add("-");
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + EMPLOYEE_COLUMN_NAME + " FROM " + EMPLOYEE;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                employeesNames.add(name);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return employeesNames;
    }

    public List<String> getEmployees() {
        ArrayList<String> employeesNames = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + EMPLOYEE_COLUMN_NAME + " FROM " + EMPLOYEE;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                employeesNames.add(name);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return employeesNames;
    }

    public List<String> getDepartmentsNames() {
        ArrayList<String> departmentsNames = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + DEPARTMENT_COLUMN_NAME + " FROM " + DEPARTMENT;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                departmentsNames.add(name);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return departmentsNames;
    }

    public List<String> getAddresses() {
        ArrayList<String> addresses = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + OFFICE_COLUMN_ADDRESS + " FROM " + OFFICE;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String address = cursor.getString(0);
                addresses.add(address);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return addresses;
    }

    public List<String> getTypes() {
        ArrayList<String> addresses = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + PRODUCT_TYPE_COLUMN_NAME + " FROM " + PRODUCT_TYPE;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                addresses.add(name);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return addresses;
    }

    public List<String> getCustomers() {
        ArrayList<String> customersNames = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + CUSTOMER_COLUMN_NAME + " FROM " + CUSTOMER;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                customersNames.add(name);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return customersNames;
    }

    public List<String> getProducts() {
        ArrayList<String> products = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + PRODUCT_COLUMN_NAME + " FROM " + PRODUCT;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                products.add(name);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }

    public int countRecords() {
        String countQuery = "SELECT * FROM " + ORDERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public int countOrdersInProductList() {
        int maxid = 0;
        String countQuery = "SELECT MAX(" + PRODUCT_LIST_COLUMN_ORDER_ID + ") FROM " + PRODUCT_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            maxid = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return maxid;
    }

    public int countOrdersInTransactionList() {
        int maxid = 0;
        String countQuery = "SELECT MAX(" + TRANSACTIONS_COLUMN_ORDER_ID + ") FROM " + TRANSACTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            maxid = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return maxid;
    }

    public int findLastDifOrderProductInList(int myOrderId) {
        int maxid = 0;
        String countQuery = "SELECT * FROM " + PRODUCT_LIST + " WHERE NOT " + PRODUCT_LIST_COLUMN_ORDER_ID + " = " + myOrderId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            maxid = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return maxid;
    }

    public int findLastDifOrderTransaction(int myOrderId) {
        String countQuery = "SELECT * FROM " + TRANSACTIONS + " WHERE NOT " + TRANSACTIONS_COLUMN_ORDER_ID + " = " + myOrderId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public TransactionModel[] getTransactionsOfOrder(int id) {
        ArrayList<TransactionModel> transactions = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + TRANSACTIONS_COLUMN_DATE + ", " + TRANSACTIONS_COLUMN_VALUE + " FROM " + TRANSACTIONS + " WHERE " + TRANSACTIONS_COLUMN_ORDER_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(0);
                String value = cursor.getString(1);
                transactions.add(new TransactionModel(date, Integer.parseInt(value), id));
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        int sizeOfArray = transactions.size();
        TransactionModel[] transactionsArray = new TransactionModel[sizeOfArray];
        for (int j = 0; j < sizeOfArray; j++) {
            transactionsArray[j] = transactions.get(j);
        }
        return transactionsArray;
    }

    public String[] getProductListOfOrder(int id) {
        ArrayList<String> productListOfOrder = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String queryString = "SELECT " + PRODUCT_LIST_COLUMN_PRODUCT_NAME + " FROM " + PRODUCT_LIST + " WHERE " + PRODUCT_LIST_COLUMN_ORDER_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        int i = 0;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                productListOfOrder.add(name);
                i++;
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        int sizeOfArray = productListOfOrder.size();
        String[] productListArray = new String[sizeOfArray];
        for (int j = 0; j < sizeOfArray; j++) {
            productListArray[j] = productListOfOrder.get(j);
        }
        return productListArray;
    }

    public void deleteCustomer(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String queryString = "DELETE FROM " + CUSTOMER + " WHERE " + CUSTOMER_COLUMN_CUST_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        ++deletedCustomers;
    }

    public void deleteProduct(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String queryString = "DELETE FROM " + PRODUCT + " WHERE " + PRODUCT_COLUMN_PROD_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        ++deletedProducts;
    }

    public void deleteEmployee(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String queryString = "DELETE FROM " + EMPLOYEE + " WHERE " + EMPLOYEE_COLUMN_EMP_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        ++deletedEmployees;
    }

    public void deleteOrder(int id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String queryString = "DELETE FROM " + ORDERS + " WHERE " + ORDER_COLUMN_ORDER_ID + " = " + id;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        ++deletedOrders;
    }

    public void deleteProductFromOrder(int id, int order_id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String queryString = "DELETE FROM " + PRODUCT_LIST + " WHERE " + PRODUCT_LIST_COLUMN_PROD_ID + " = " + id + " AND " + PRODUCT_LIST_COLUMN_ORDER_ID + " = " + order_id;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
    }

    public void deleteTransactionFromOrder(int id, int order_id) {
        SQLiteDatabase db = database.getWritableDatabase();
        String queryString = "DELETE FROM " + TRANSACTIONS + " WHERE " + TRANSACTIONS_COLUMN_TR_ID + " = " + id + " AND " + TRANSACTIONS_COLUMN_ORDER_ID + " = " + order_id;
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
    }

    public void updateCustomer(CustomModel customModel) {
        String id = String.valueOf(customModel.getCust_id() + 1 + getDeletedCustomers());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_COLUMN_NAME, customModel.getName());
        cv.put(CUSTOMER_COLUMN_POSTAL, customModel.getPostal());
        cv.put(CUSTOMER_COLUMN_STATE, customModel.getState());
        cv.put(CUSTOMER_COLUMN_CITY, customModel.getCity());
        cv.put(CUSTOMER_COLUMN_ADDRESS, customModel.getAddress());

        db.update(CUSTOMER, cv, "CUST_ID = ?", new String[]{id});
    }

    public void updateProduct(ProductModel productModel) {
        String id = String.valueOf(productModel.getProd_id() + 1 + getDeletedProducts());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_COLUMN_NAME, productModel.getName());
        cv.put(PRODUCT_COLUMN_TYPE, productModel.getType());
        cv.put(PRODUCT_COLUMN_START_DATE, productModel.getStartDate());
        cv.put(PRODUCT_COLUMN_END_DATE, productModel.getEndDate());

        db.update(PRODUCT, cv, "PROD_ID = ?", new String[]{id});
    }

    public void updateEmployee(EmpModel empModel) {
        String id = String.valueOf(empModel.getEmp_id() + 1 + getDeletedEmployees());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EMPLOYEE_COLUMN_NAME, empModel.getName());
        cv.put(EMPLOYEE_COLUMN_TITLE, empModel.getTitle());
        cv.put(EMPLOYEE_COLUMN_DEPARTMENT, empModel.getDepartment());
        cv.put(EMPLOYEE_COLUMN_OFFICE, empModel.getOffice());
        cv.put(EMPLOYEE_COLUMN_SUPERIOR, empModel.getSuperior());
        cv.put(EMPLOYEE_COLUMN_START_DATE, empModel.getStartDate());
        cv.put(EMPLOYEE_COLUMN_END_DATE, empModel.getEndDate());

        db.update(EMPLOYEE, cv, "EMP_ID = ?", new String[]{id});
    }

    public void updateOrder(OrderModel orderModel) {
        String id = String.valueOf(orderModel.getOrder_id());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ORDER_COLUMN_CUSTOMER, orderModel.getCustomer());
        cv.put(ORDER_COLUMN_STATUS, orderModel.getStatus());
        cv.put(ORDER_COLUMN_EMPLOYEE, orderModel.getEmp());
        cv.put(ORDER_COLUMN_START_DATE, orderModel.getStartDate());
        cv.put(ORDER_COLUMN_END_DATE, orderModel.getEndDate());
        cv.put(ORDER_COLUMN_SUM, orderModel.getSum());

        db.update(ORDERS, cv, "ORDER_ID = ?", new String[]{id});
    }

}
