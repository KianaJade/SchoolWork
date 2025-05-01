export function accountInputCheck(account){
    return account.replace(/[^0-9]/g, '');
}

export function passwordInputCheck(password){
    return  password.replace(/[^!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~a-zA-Z0-9]/g, '');
}

export function priceInputCheck(price){
    return price.replace(/[^.0-9]/g, '');
}
