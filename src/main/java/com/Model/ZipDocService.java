package com.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipDocService {

    @Autowired
    private ZipRepository zipRepository;
    @Autowired
    private DocRepository docRepository;

    public void addZip(Zip z){
        zipRepository.save(z);
    }

    public void deleteZip(long id){
        zipRepository.delete(id);
    }

    public void changeZipName(long idZ, String n){
        Zip z = zipRepository.findOne(idZ);
        if(z!=null) {
            z.setName(n);
            zipRepository.save(z);
        }
    }

    public Zip getZip(long id){
        return zipRepository.findOne(id);
    }

    public void saveZip(Zip z){
        zipRepository.save(z);
    }

    public Iterable<Zip> getAllZips(){
        return zipRepository.findAll();
    }

    public void addDoc(long idZ, Doc d)    {
        Zip z = zipRepository.findOne(idZ);
        if(z!=null){
            d.setZip(z);
            z.addDoc(d);

            docRepository.save(d);
            zipRepository.save(z);
        }
    }

    public void deleteDoc(long idZ, long idD){
        Zip z = zipRepository.findOne(idZ);
        if(z!=null){
            //Doc d = docRepository.findOne(idD);
            //if(d!=null){
                //we don't need do delete doc directly from the repository,
                // because we have orphanRemoval = true in Zip class
                z.removeDoc(idD);
                zipRepository.save(z);
                //}
        }
    }

    public void changeZipDocContent(/*long idZ, */long idD, String cont){
        //Zip z = zipRepository.findOne(idZ);
        //if(z!=null){
            Doc d = docRepository.findOne(idD); //z.findDocByID(idD);
            if(d!=null){
                d.setContent(cont);
                docRepository.save(d);
            }
        //}
    }

    public Doc getDoc(long idZ, long idD)
    {
        Zip z = zipRepository.findOne(idZ);
        if(z!=null) {
            return z.findDocByID(idD);
        }
        return null;
    }

    public Iterable<Doc> getZipDocs(long idZ){
        Zip z = zipRepository.findOne(idZ);
        if(z!=null) {
            return z.getOrderedDocs();
        }
        return  null;
    }
}
