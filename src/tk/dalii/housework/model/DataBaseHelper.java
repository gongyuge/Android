package tk.dalii.housework.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * �Զ���DataBaseHelper��
 * 
 * @author zhi
 * 
 */
public class DataBaseHelper extends SQLiteOpenHelper {

	// database name
	private static final String NAME = "housework.db";
	// database version
	private static final int VERSION = 1;
	// ��ʼ������������
	private String[] houseworks = { "ɨ��", "ϴ��", "����", "���", "ϴ�·�", "������",
			"����", "Ъ��", "����", "����" };

	// database ���캯��
	public DataBaseHelper(Context context) {
		super(context, NAME, null, VERSION);
	}

	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, NAME, factory, VERSION);
	}

	/**
	 * ��һ�δ������ݿ���ʱ�򱻵��� ���� hw_selected��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// �½�hw_selected���洢��Ϊgridview��ѡ����
		db.execSQL("CREATE TABLE hw_selected("
				+ "id integer primary key autoincrement,hwsid integer," + "name varchar(16)"
				+ ")");
		// �½�hw_houseworks���洢��Ϊlistview��ѡ����--���м�����Ŀ
		db.execSQL("CREATE TABLE hw_houseworks("
				+ "id integer primary key autoincrement," + "name varchar(16)"
				+ ")");
		// ��ʼ������
		for (int i = 0; i < houseworks.length; i++) {
			//��ʼ����hw_houseworks
			db.execSQL("INSERT INTO hw_houseworks(name) values(?)",
					new Object[] { houseworks[i]});
			//��ʼ����hw_selected
			if(i<6){
				db.execSQL("INSERT INTO hw_selected(hwsid,name) values(?,?)",
						new Object[] { i+1,houseworks[i]});
			}
		}
	}

	/**
	 * ���ݿ�汾���� ɾ��ԭ�� ���´���
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS hw_selected");
		db.execSQL("DROP TABLE IF EXISTS hw_houseworks");
		onCreate(db);
	}

}
