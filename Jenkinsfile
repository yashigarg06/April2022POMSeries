pipeline{
    agent any
    stages{
        stage("Build"){
           steps{
                echo("build the project")
           }
        }
        stage("Deploy on DEV"){
           steps{
                echo("Deploy on DEV")
           }
        }
        stage("Deploy on QA"){
           steps{
                echo("Deploy on QA")
           }
        }
        stage("Run regression test cases on QA"){
           steps{
                echo("Running regression test cases on QA")
           }
        }
        stage("Deploy on STAGE"){
           steps{
                echo("Deploy on STAGE")
           }
        }
        stage("Run sanity test cases on STAGE"){
           steps{
                echo("Running sanity test cases on STAGE")
           }
        }
        stage("Deploy on PROD"){
           steps{
                echo("Deploy on PROD")
           }
        }
    }
}