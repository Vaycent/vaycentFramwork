package vaycent.vaycentproject.DemoPackage.IPC_Package;

import vaycent.vaycentproject.DemoPackage.IPC_Package.Book;


interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
