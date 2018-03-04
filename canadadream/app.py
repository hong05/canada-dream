from flask import Flask,render_template,flash,redirect,url_for,session,logging,request
from data import Powders
from flask_mysqldb import MySQL
from wtforms import Form,StringField,TextAreaField,PasswordField,validators
from passlib.hash import sha256_crypt

app = Flask(__name__)

#config MySQL
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = 'wxwh0505'
app.config['MYSQL_DB'] = 'canadadream'
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'
#init MYSQL
mysql = MySQL(app)


Powders = Powders()

@app.route('/')
def index():
    return render_template('home.html')

@app.route('/about')
def about():
    return render_template('about.html')

@app.route('/powders')
def powders():
    return render_template('powders.html',powders=Powders)

@app.route('/powder/<string:id>/')
def powder(id):
    return render_template('powder.html',id=id)

class RegisterForm(Form):
    name = StringField('Name',[validators.Length(min=1,max=50)])
    username = StringField('Username',[validators.Length(min=1,max=20)])
    password = PasswordField('Password',[validators.Length(min=1,max=20)])
    email = StringField('Email',[validators.DataRequired()])

@app.route('/register',methods = ['GET','POST'],)
def register():
    form = RegisterForm(request.form)
    if request.method =='POST' and form.validate():
        name = form.name.data
        username =form.username.data
        password =sha256_crypt.encrypt(str(form.password.data))
        email=form.email.data

        #create cursor
        cur = mysql.connection.cursor()
        cur.execute("INSERT INTO users(name,username,password,email) Values(%s,%s,%s,%s)",(name,username,password,email))

        #commit to db
        mysql.connection.commit()

        cur.close()
        flash("Successfully Registered",'success')
        return redirect(url_for('index'))

    return render_template('register.html',form=form)


if __name__=='__main__':
    app.secret_key = 'canadadream'
    app.run(debug=True)
