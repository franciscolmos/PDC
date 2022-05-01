var Table = {
    id: 1,
    add: function(){
        for(var i = 0; i < this.id; ++i){
            var p = this.createPerson($("#"+i))
            if(this.checkEmpty(p) == false){
                if(p.email.indexOf("@") != -1 || p.alive == "no"){
                    var local = JSON.parse(localStorage.getItem(i))
                    if (local != null){
                        if(JSON.stringify(local) !== JSON.stringify(p)){
                            localStorage.removeItem(i)
                            if(this.checkUnit(p)){
                                localStorage.setItem(i, JSON.stringify(p))
                            }
                            else{
                                localStorage.setItem(i, JSON.stringify(local))
                                alert("Dato Editado Existe")
                            }
                        }
                    }
                    else{
                        if(this.checkUnit(p)){
                            localStorage.setItem(this.id, JSON.stringify(p))
                        }
                        else{
                            alert("Dato Nuevo Ingresado Existe")
                        }
                    }
                }
                else{
                    alert("Mail no valido")
                }
            }
            else{
                if(i != 0) alert("Elementos Vacios")
            }
        }
        this.data()
    },
    createPerson: function(row){
        var p = {
            surname:  row.find("#isurname").val(),
            name:  row.find("#iname").val(),
            parent:  row.find("#iparent").val(),
            alive:  row.find("#ialive:checked").val(),
            birth:  row.find("#ibirth").val(),
            ocupation:  row.find("#iocupation").val(),
            email:   row.find("#iemail").val()
        }
        return p
    },
    data: function(){
        row = this.createRow({surname: "", name: "", parent: "", alive: "si", birth: "", ocupation: "", email: ""}, 0)
        if($("#0").length) $("#0").remove()
        $("#table").append(row)

        for(var i = 0; i < localStorage.length; ++i){
            var key = localStorage.key(i);
            var p = JSON.parse(localStorage.getItem(key));
            this.id = parseInt(key) + 1
            if(!$("#"+key).length){
                this.createRow(p, key);
                $("#table").prepend(row)
            }
        }
    },
    createRow: function(p, pos){
        var border = {'border': '1px solid black', 'background-color': "yellow"}
        if (pos % 2 == 0)  border = {'border': '1px solid black', 'background-color': "#E9E9E9"}
        row = $("<div class=\"row\" id="+pos+"> </div>").css(border)
        surname = this.createSurname(p.surname, pos)
        names = this.createName(p.name, pos)
        parent = this.createParent(p.parent, pos)
        alive = this.createAlive(p.alive, pos)
        birth = this.createBirth(p.birth, pos)
        ocupation = this.createOcupation(p.ocupation, p.alive, pos)
        email = this.createEmail(p.email, p.alive, pos)
        image = this.createImage()
        row.append(surname, names, parent, alive, birth, ocupation, email, image)
        return row
    },
    delete: function(del){
        var id = $(del).closest(".row").attr("id")
        localStorage.removeItem(id);
        aux = []
        for(var i = 0; i < localStorage.length; ++i){ 
            var key = localStorage.key(i);
            aux.push(JSON.parse(localStorage.getItem(key)));
        }
        localStorage.clear()
        this.id = 1
        for(var i = 0; i < aux.length; ++i){
            localStorage.setItem(this.id, JSON.stringify($(aux)[i]))
            this.id++
        }
        location.reload()
    },
    deleteAll: function(){
        localStorage.clear()
        location.reload()
    },
    checkAlive: function(radio){
        var id = $(radio).closest(".row").attr("id")
        if($("#"+id).find("#ialive").prop("checked") == false){
            $("#"+id).find("#iocupation").prop("disabled", true)
            $("#"+id).find("#iocupation").prop("required", false)
            $("#"+id).find("#iocupation").val("")
            $("#"+id).find("#iemail").prop("disabled", true)
            $("#"+id).find("#iemail").prop("required", false)
            $("#"+id).find("#iemail").val("")
            return;
        }
        $("#"+id).find("#iocupation").prop("disabled", false)
        $("#"+id).find("#iocupation").prop("required", true)
        $("#"+id).find("#iemail").prop("disabled", false)
        $("#"+id).find("#iemail").prop("required", true)
    },
    checkUnit: function (p) {
        for(var i = 0; i < localStorage.length; ++i){
            var key = localStorage.key(i);
            var local = JSON.parse(localStorage.getItem(key));
            if (local.surname == p.surname && local.name == p.name && local.parent == p.parent){
                return false
            }
        }
        return true
    },
    checkEmpty: function (p){
        if (p.alive == "si"){
            if(p.surname == ""|| p.name == "" || p.parent == "" || p.birth == "" || p.ocupation == "" || p.email == ""){
                return true
            }
        }
        else{
            if(p.surname == ""|| p.name == "" || p.parent == "" || p.birth == ""){
                return true
            }
        }
        return false
    },
    createSurname: function(value, pos){
        return $("<div class=\"col\"><input type=\"text\" id=\"isurname\" name=\""+pos+"surname\" class=\"form-control\" value=\""+value+"\" maxlength=\"40\" required></div>")
    },
    createName: function(value, pos){
        return $("<div class=\"col\"><input type=\"text\" id=\"iname\" name=\""+pos+"name\" class=\"form-control\" value=\""+value+"\" maxlength=\"100\" required></div>")
    },
    createParent: function(value, pos){
        return $("<div class=\"col\"> \
            <select id=\"iparent\" name=\""+pos+"parent\" class=\"form-control\" required> \
                <option value=\"\"> Seleccione </option> \
                <option value=\"Madre\""+ ((value == "Madre") ? "selected" : "") +"> Madre </option> \
                <option value=\"Padre\""+ ((value == "Padre") ? "selected" : "") +"> Padre </option> \
                <option value=\"Hermano\""+ ((value == "Hermano") ? "selected" : "") +"> Hermaano/a </option> \
                <option value=\"Conyuge\""+ ((value == "Conyuge") ? "selected" : "") +"> Conyuge </option> \
            </select> \
        </div>")
    },
    createAlive: function(value, pos){
        return $("<div class=\"col radio align-self-center\" onchange=\"Table.checkAlive(this)\">\
                    <label> si <input type=\"radio\" id=\"ialive\" name=\""+pos+"alive\" value=\"si\""+ ((value == "si") ? "checked" : "") +"></label>\
                    <label >no <input type=\"radio\" id=\"ialive\" name=\""+pos+"alive\" value=\"no\""+ ((value == "no") ? "checked" : "") +"></label>\
                </div>")
    },
    createBirth: function(value, pos){
        return $("<div class=\"col\"><input type=\"number\" id=\"ibirth\" name=\""+pos+"birth\" class=\"form-control\" value=\""+value+"\" min=\"0\" max=\"2019\"></div>")
    },
    createOcupation: function(ocup, alive, pos){
        return $("<div class=\"col\"> \
                    <select id=\"iocupation\" name=\""+pos+"ocupation\" class=\"form-control\""+ ((alive == "si") ? "required" : "disabled") +"> \
                        <option value=\"\"> Seleccione </option> \
                        <option value=\"Empleado\""+ ((ocup == "Empleado") ? "selected" : "") +"> Empleado </option> \
                        <option value=\"Empresario\""+ ((ocup == "Empresario") ? "selected" : "") +"> Empresario </option> \
                        <option value=\"Independiente\""+ ((ocup == "Independiente") ? "selected" : "") +"> Independiente </option> \
                        <option value=\"jubilado\""+ ((ocup == "Jubilado") ? "selected" : "") +"> jubilado </option> \
                        <option value=\"No Trabaja\""+ ((ocup == "No Trabaja") ? "selected" : "") +"> No Trabaja</option> \
                    </select> \
                </div>")
    },
    createEmail: function(email, alive, pos){
        return $("<div class=\"col\"> <input type=\"email\" id=\"iemail\" name=\""+pos+"email\" class=\"form-control\" value=\""+email+"\" maxlength=\"255\" "+ ((alive == "si") ? "required" : "disabled") +"> </div>")
    },
    createImage: function(){
        return $("<div class=\"col\"><img src=\"image/delete.png\" width=\"40\" height=\"35\" onclick=\"Table.delete(this)\"></div>")
    }
}