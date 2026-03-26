def loginDocker() {

    def checkStatus = false
    withCredentials([usernamePassword (
        credentialsID: 'loginPassword',
        usernameVariable: 'username',
        passwordVariable: 'password'
    )]) {

        def result = sh(script: docker login -u $username -p $password, returnStatus: true).trim()

        if (result == 0) {
            checkStatus = true


        } else {

            checkStatus = false
        }

    }
    return checkStatus


}