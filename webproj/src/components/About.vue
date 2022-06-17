<template>
    <div id='about'>
        <section id="main">
            <div class="container">
                <br>
               <article id="main-col">
                <br>
                    <h1 style="font-size:40px; color:black;" class="dark"><b><center>Kupovinom jedne članarine dobijate pristup stotinama najboljih teretana, fitnes studija, bazena i sportskih centara širom Srbije.</center></b></h1>
                     
                        <br><b><p style="font-size:40px; color:black;" >
                        <center> Pristup stotinama najboljih teretana, fitnes studija,
                        bazena i sportskih centara
                        širom Srbije</center>
                        
                    </p></b>
                    <p style="font-size:40px; color:black;" class="dark">
                        <b><center>Pristup 500+ video vežbi,
                            200+ treninga i poseban fitnes
                            program za svakog korisnika</center></b>
                        
                    </p>
                   
               </article>

               <aside id="sidebar">
                    <div class="dark">
                        <img src="../assets/logo.png" width="330" height="380">
                         <p style="font-size:40px; color:black;" class="dark">
                        <center>Jedna članarina je dovoljna za sve vaše fitnes potrebe!</center>
                    </p>
                    </div>
                </aside>

            </div>
        </section>
    </div>
</template>
<script>

export default {
    name:'About',
    methods:{

    }
}
</script>
<style scoped>
body{
    background-color:#f4f4f4;
    margin:0;
    padding:0;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 15px;
    line-height: 1.5em;
    /*isto*/
    font: 15px 1.5em Arial, Helvetica, sans-serif;
}

/*global*/
.container{
    width:80%;
    margin:auto;
    overflow: hidden;
}


.dark{
    padding:15px;
    background-color:#D3D3D3;
    color:#fff;
    margin:10px 0;
}


/* Ostaviti za sada ako ne obrisati */
/*Sidebar*/
aside#sidebar{
    float: right;
    width:33%;
    margin-top:10px;
}

article#main-col{
    float: left;
    width:65%;
}
</style>