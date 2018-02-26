from flask import Flask,render_template
from data import Powders

app = Flask(__name__)

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

if __name__=='__main__':
    app.run(debug=True)
