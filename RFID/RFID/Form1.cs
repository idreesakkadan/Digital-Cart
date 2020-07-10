using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using MySql.Data;
using MySql.Data.MySqlClient;


namespace RFID
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        string sql = "";

        MySqlConnection con = new MySqlConnection("server=localhost;database=digital_cart;username=root;password=''");
        private void execute(string sql)
        {
            MySqlCommand cm = new MySqlCommand(sql, con);
            try
            {
                con.Open();
                cm.ExecuteNonQuery();
               // MessageBox.Show("Ok");
            }
            catch (Exception ex)
            {
               // MessageBox.Show(ex.ToString());

            }
            finally
            {
                con.Close();
            }
        }

        private int  executescal(string sql)
        {
            int id = 0;
            MySqlCommand cm = new MySqlCommand(sql, con);
            try
            {
                con.Open();
                id = Convert.ToInt32(cm.ExecuteScalar());
                // MessageBox.Show("Ok");
            }
            catch (Exception ex)
            {
                id = 1;
               // MessageBox.Show(ex.ToString());

            }
            finally
            {
                con.Close();
            }
            return id;
        }

        private DataTable getdata(string sql)
        {
            MySqlDataAdapter da = new MySqlDataAdapter(sql, con);
            DataSet ds = new DataSet();
            da.Fill(ds);
            return ds.Tables[0];
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (serialPort1.IsOpen)
            {
                serialPort1.Close();
            }
            try
            {
                serialPort1.PortName = comboBox1.Text;
                serialPort1.Open();
                MessageBox.Show("Port Opened Successfully");
            }
            catch (Exception ex)
            {
                //MessageBox.Show("Error");
            }
        }

        string sin;
        private delegate void printres();
        char c1 = (char)10;
        char c2 = (char)13;

        string pname = "";


        decimal tot = 0;


        private void showres()
        {
            textBox4.Text = textBox4.Text + sin;
            textBox5.Text = textBox5.Text + sin;
            string rfid = "72 31 FF 1A";





            sql = "select * from product_details where productid=55";
            DataTable dt = getdata(sql);

            if (dt.Rows.Count > 0)
            {
                pname = dt.Rows[0][2].ToString();

                if (textBox4.Text.Contains("+"))
                {
                    DataRow dr = dt.Rows[0];
                    sql = "select * from cart_temp where pid=" + dr[0].ToString();
                    DataTable dt1 = getdata(sql);

                    if (dt1.Rows.Count > 0)
                    {
                        DataRow dr1 = dt1.Rows[0];
                        sql = "update cart_temp set quantity=quantity+1 where pid=" + dr1[1].ToString();
                        execute(sql);
                        showcart();
                        totcalc();
                     //   MessageBox.Show("+");
                    }
                    else
                    {

                        sql = "insert into cart_temp values(1,'" + dr[0].ToString() + "',1," + dr[3].ToString() + ")";//4
                        execute(sql);
                        showcart();
                        totcalc();
                    }
                    textBox4.Clear();

                }
                else if (textBox4.Text.Contains("-"))
                {
                    DataRow dr = dt.Rows[0];
                    sql = "select * from cart_temp where pid=" + dr[0].ToString();
                    DataTable dt1 = getdata(sql);
                    if (dt1.Rows.Count > 0)
                    {
                        DataRow dr1 = dt1.Rows[0];
                        sql = "update cart_temp set quantity=quantity-1 where pid=" + dr1[1].ToString();
                        execute(sql);
                        showcart();
                        totcalc();
                       // MessageBox.Show("-");
                    }
                    textBox4.Clear();
                }


            }
        }


        private void totcalc()
        {
            tot = 0;
            foreach (DataGridViewRow dvr in dataGridView1.Rows)
            {

                tot += decimal.Parse(dvr.Cells[3].Value.ToString());
                textBox6.Text = tot.ToString();

            }

        }


        private void showcart()
        {
            sql = "select a.*,product_details.productname from cart_temp as a,product_details where a.pid=product_details.productid";
            dataGridView1.DataSource = getdata(sql);

            totcalc();
        }

        private void serialPort1_DataReceived(object sender, System.IO.Ports.SerialDataReceivedEventArgs e)
        {
            sin = serialPort1.ReadExisting();
            textBox1.Invoke(new printres(showres));
        }

        private void button2_Click(object sender, EventArgs e)
        {
            textBox1.Clear();
            //MessageBox.Show(rf);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            showcart();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            sql = "delete from cart_temp";
            execute(sql);
            showcart();
            //serialPort1.Write("G");
        }

        private void button3_Click(object sender, EventArgs e)
        {
            sql = "select max(Bill_no) from s_billdetails";
            int billno = executescal(sql)+1;
            decimal amt=0;

            foreach (DataGridViewRow gr in dataGridView1.Rows)
            {
                sql = "select max(cart_id) from cart_temp";
                int carid = executescal(sql) + 1;

                //sql = "insert into cart_temp values(" + carid + "," + gr.Cells[1].Value.ToString() + ",'" + gr.Cells[2].Value.ToString() + "','" + gr.Cells[3].Value.ToString() + "','confirm','" + billno.ToString() + "')";
                sql = "insert into cart_temp values(" + carid + "," + gr.Cells[1].Value.ToString() + ",'" + gr.Cells[2].Value.ToString() + "','" + gr.Cells[3].Value.ToString() + "')";
                execute(sql);
                amt = amt + (decimal.Parse(gr.Cells[2].Value.ToString()) * decimal.Parse(gr.Cells[3].Value.ToString()));
            }
           // sql = "select * from cart_temp";
            sql = "insert into s_billdetails values(" + billno + ",'" + textBox3.Text + "','" + amt + "','" + DateTime.Now.ToShortDateString() + "','confirm')";
            execute(sql);
            sql = "delete from cart_temp";
            execute(sql);
            showcart();

        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
