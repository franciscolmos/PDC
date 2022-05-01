function cambiarLista(li) {
    const parent = li.closest('ul')
    parent.id == 'list1' ? (li.remove() , $('#list2').append(li)) : (li.remove(), $('#list1').append(li))
}