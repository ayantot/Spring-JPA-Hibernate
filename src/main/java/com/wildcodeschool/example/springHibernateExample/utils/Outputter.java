package com.wildcodeschool.example.springHibernateExample.utils;

import com.wildcodeschool.example.springHibernateExample.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wildcodeschool.example.springHibernateExample.repositories.StudentRepository;


@Component
public class Outputter implements CommandLineRunner {

    private Logger LOG = LoggerFactory.getLogger("Thomas");

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {

        // Checke combien d'objets se trouvent dans la BDD
        LOG.info("******************");
        LOG.info("Objects in DB : " + studentRepository.count());

        // Crée un nouvel utilisateur et l'enregistre dans la BDD
        Student student1 = new Student("Jean-Marie", "Bernard", 50);
        LOG.info("******************");
        LOG.info( student1+ " has been created !");
        studentRepository.save(student1);
        LOG.info(student1 + " has been saved !");

        // Crée un second utilisateur et l'enregistre dans la BDD
        Student student2 = new Student("Brandon", "Wilder", 33);
        LOG.info("******************");
        LOG.info(student2 + " has been created !");
        studentRepository.save(student2);
        LOG.info(student2 + " has been saved !");

        // Lit les informations correspondant au second utilisateur
        Student tempStudent = studentRepository.findById(2L).get(); /* On écrit "2L" car
                                                       le type de l'id est Long */
        LOG.info("******************");
        LOG.info("Second student's firstName is " + tempStudent.getFirstName());
        LOG.info("Second student's lastName is " + tempStudent.getLastName());
        LOG.info("Second student's age is " + tempStudent.getAge());

        // Liste les utilisateurs enregistrés dans la BDD
        LOG.info("******************");
        LOG.info("Students in list are ");
        for (Student myStudent : studentRepository.findAll()) {
            LOG.info(myStudent.toString());
        }
        ;

        // Supprime le second utilisateur de la BDD
        studentRepository.deleteById(2L); /* risque de provoquer une erreur si
                                tu n'as pas vidé ta table avant de relancer
                                ton application ! */

        /*     Liste les utilisateurs enregistrés dans la BDD
             (permet de vérifier que le second utilisateur
             a bien été supprimé de la BDD) */
        LOG.info("******************");
        LOG.info("Students in list are ");
        for (Student myStudent : studentRepository.findAll()) {
            LOG.info(myStudent.toString());
        }

    }
}