def call(Map pipelineParams = [:]) {

    pipeline {

        agent any

        environment {
            WORKSPACE = "/scm/checkout/TIM/${JOB_NAME}"
            NODE = "23222222"

            DOCKER_IMAGE = "${pipelineParams.DOCKER_IMAGE ?: 'nginx'}"
            DOCKER_TAG   = "${pipelineParams.DOCKER_TAG ?: 'latest'}"
        }

        stages {
            stage('Print Values') {
                steps {
                    echo "Workspace: ${env.WORKSPACE}"
                    echo "Node: ${env.NODE}"
                        echo "Image: ${env.DOCKER_IMAGE}"
                    echo "Tag: ${env.DOCKER_TAG}"
                }
            }
            stage('login to docker') {
                steps {
                    script {
                    def status = dockerLogin()
                    if (status = true) {
                        sh """
                        docker build -t ${env.DOCKER_IMAGE}:${env.DOCKER_TAG} .
                        """
                    } else {
                        error "Docker login failed"


                    }
                
                }
            }
            

        }
            
        }

    }
}
