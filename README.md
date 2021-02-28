# Currency converter
Description: a site backed by a RESTful API that convert currency with value.
* Input
    * A source currency
    * A target currency
    * A monetary value
* Output
    * float monetary value of target currency
###The API leverages the exchange rates provided at https://exchangeratesapi.io

#API address
https://currency-converter-mxie.herokuapp.com/convert?source={source}&target={target}&amount={amount}
## Example
https://currency-converter-mxie.herokuapp.com/convert?source=USD&target=CNY&amount=400


#Conclusion
1. Spring boot
2. RESTful API
3. Maven
4. Deployed to Heroku by Git, check from https://devcenter.heroku.com/articles/git
