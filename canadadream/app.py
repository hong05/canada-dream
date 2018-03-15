from flask import Flask,render_template,flash,redirect,url_for,session,logging,request
from data import Powders
from flask_mysqldb import MySQL
from wtforms import Form,StringField,TextAreaField,PasswordField,validators
from passlib.hash import sha256_crypt
from functools import wraps

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
    return render_template('index.html')

@app.route('/powders')
def powders():
    powdersList=[]
    cur = mysql.connection.cursor()
    result = cur.execute('SELECT * from powders')
    while True:
        row = cur.fetchone()
        if row == None:
            break
        powdersList.append(row)
    print powdersList

    return render_template('powders.html',powders=powdersList)

@app.route('/powderBYbrand/<string:brand>/')
def powderBYbrand(brand):
    powdersList=[]
    cur = mysql.connection.cursor()
    result = cur.execute('SELECT * from powders WHERE brand=%s',[brand])
    while True:
        row = cur.fetchone()
        if row == None:
            break
        powdersList.append(row)
    print powdersList
    return render_template('powder.html',powders=powdersList)

@app.route('/powderBYstage/<string:stage>/')
def powderBYstage(stage):
    powdersList=[]
    cur = mysql.connection.cursor()
    result = cur.execute('SELECT * from powders WHERE stage=%s',[stage])
    while True:
        row = cur.fetchone()
        if row == None:
            break
        powdersList.append(row)
    print powdersList
    return render_template('powder.html',powders=powdersList)

@app.route('/search', methods=['GET','POST'])
def search():
    if request.method =='POST':
        keyword = request.form['search']
        print keyword
    productList=[]
    cur = mysql.connection.cursor()
    #result = cur.execute('SELECT * from powders WHERE brand = %s',[keyword])
    stmt = 'SELECT * FROM powders WHERE name LIKE %s OR brand Like %s'
    args= ['%'+keyword+'%','%'+keyword+'%']
    result=cur.execute(stmt,args)
    
    while True:
        row = cur.fetchone()
        if row == None:
            break
        productList.append(row)
    print productList

    return render_template('search.html',productList=productList, keyword=keyword)


class RegisterForm(Form):
    username = StringField('Username',[validators.Length(min=1,max=20)])
    password = PasswordField('Password',[validators.Length(min=1,max=20)])
    email = StringField('Email',[validators.DataRequired()])

@app.route('/register',methods = ['GET','POST'])
def register():
    form = RegisterForm(request.form)
    if request.method =='POST' and form.validate():
        username =form.username.data
        password =sha256_crypt.encrypt(str(form.password.data))
        email=form.email.data

        #create cursor
        cur = mysql.connection.cursor()
        cur.execute('INSERT INTO users(username,password,email) Values(%s,%s,%s)',(username,password,email))

        #commit to db
        mysql.connection.commit()

        cur.close()
        flash('Successfully Registered','success')
        return redirect(url_for('index'))

    return render_template('register.html',form=form)

@app.route('/login',methods=['GET','POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        passwordInput = request.form['password']

        cur = mysql.connection.cursor()
        result = cur.execute('SELECT * from users WHERE username=%s',[username])
        if result > 0:
            data = cur.fetchone()
            password = data['password']
            if sha256_crypt.verify(passwordInput,password):
                session['logged_in'] = 'True'
                session['username'] = username
                flash('You are now logged in','success')
                return redirect(url_for('index'))

            else:
                return render_template('login.html',error='password incorrect')
        else:
            return render_template('login.html',error='user not found')

    return render_template('login.html')

@app.route('/logout')
def logout():
    session.clear()
    flash('You are now logged out','success')
    return redirect(url_for('login'))

@app.route('/cart')
def cart():
    return render_template('cart.html')


if __name__=='__main__':
    app.secret_key = 'canadadream'
    app.run(debug=True)
