pipeline {
    properties([parameters([string(defaultValue: '127.0.0.7', description: 'Please, provide an IP to host a Website', name: 'IP', trim: false)])])
    stage("Install git"){
        sh "ssh ec2-user@${IP}    sudo yum install git python-pip -y"
    }
     stage("Clone a repo"){
        git "https://github.com/tilenbaeva/flask-examples.git"
    }
    stage("Copy files"){
        sh "scp * ec2-user@${IP}:/tmp/"
    }
    stage("Install requirements"){
        sh "sudo ec2-user@${IP}     sudo  pip install -r /tmp/requirements.txt"
    }
    stage("Run App"){
        sh "ssh ec2-user@${IP}  python /tmp/01-hello-world/hello.py"
    }
}